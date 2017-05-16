package com.feicui.teach.sockettell.utils;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import android.util.Log;

import java.io.IOException;

import org.lansir.ip.codecs.Speex;

/**
 * Created by Administrator on 2017/5/14 0014.
 * 声音播放
 */

public class VoicePlayer extends Thread {

    /**
     * 用于进行声音播放
     */
    public AudioTrack  mAudioTrack;

    /**
     * 是否停止接收
     */
    private boolean isStopReceiver;

    UDPUtil udp;

    Context mContext;

    private short[] shortBuffer = new short[' '];

    public VoicePlayer(Context context){
        mContext=context;
    }


    /**
     * 播放录音数据
     */
    public void playAudio(){

        /**
         *初始化播放器
         */
        initAudioTrack();
        /**
         * 初始化Socket
         */
        try {
             udp=new UDPUtil();
            udp.initSocketReceiverData();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // 刷新过滤列表, 本机IP可能改变
        VoiceFilter.refreshFilterList();
        /**
         * 初始化接收数据包
         */
        byte[] byteBuffer=null;
        if(SystemSettings.USE_SPEEX&&(Build.CPU_ABI.equals("armeabi")||Build.CPU_ABI.equals("x86"))) {
            byteBuffer = new byte[Speex.getCompressionValue(SystemSettings.SPEEX_QUALITY)];

        } else {
            byteBuffer = new byte[320];
        }


        udp.packReceiverData(byteBuffer);

        Log.e("aaaa", "mAudioTrack=="+"执行run方法"+isStopReceiver );


        /**
         * 循环使用UDP接收信息并且播放
         */
        while(!isStopReceiver) {

            udp.getVoiceData();
            //用AudioTrack来播放声音
            Log.e("aaaa", "mAudioTrack=="+mAudioTrack );
//            Toast.makeText(mContext, "--接收到信息--", Toast.LENGTH_SHORT).show();
            //如果设置为回放
            Log.e("aaaa", "playAudio: +udp=="+udp+"mPacket=="+udp.mPacket.getAddress());
            //如果设置为回放  默认false    不回放&&接收数据的IP是当前的ip----->不播放
            /**
             * 去除回音
             */
            if(udp.mPacket.getAddress()!=null){
                Log.e("aaaa", "playAudio: 接收到数据的来源地址----"+udp.mPacket.getAddress());
                if(!SystemSettings.IS_ECHO &&udp.mPacket.getAddress()!=null&& VoiceFilter.isInFilterList(udp.mPacket.getAddress().getHostAddress())) {
                    continue;
                }
            }




//            if(mAudioTrack!=null){//防止线程安全问题
                if (SystemSettings.USE_SPEEX&&(Build.CPU_ABI.equals("armeabi")||Build.CPU_ABI.equals("x86"))) {
                    Speex.decode(byteBuffer, byteBuffer.length, shortBuffer);
                    mAudioTrack.write(shortBuffer, 0, 160);
                } else {
                    Log.e("aaaa", "playAudio: mAudioTrack="+mAudioTrack+"isStopReceiver="+isStopReceiver );
                    Log.e("bbbbb", "playAudio: "+byteBuffer );
                    mAudioTrack.write(byteBuffer, 0, 320);
                    Log.e("aaaa", "playAudio: 读取完毕" );
                }
            }
                Log.e("aaa", "playAudio: "+"循环结束" );
                releaseAudioTrack();

//        }
    }


    /**
     * 初始化录音播放
     */
    public void initAudioTrack(){
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, 2, AudioFormat.ENCODING_PCM_16BIT, /*Config.AUDIO_TRACK_BUFFER*/24576, AudioTrack.MODE_STREAM);
        mAudioTrack.play();
    }

    /**
     * 回收播放器
     */
    public  void releaseAudioTrack(){

        udp.socketErrorFinish();
        if(mAudioTrack != null) {
            if(mAudioTrack.getPlayState() != AudioTrack.PLAYSTATE_STOPPED) {
                mAudioTrack.stop();
            }
            mAudioTrack.release();
            mAudioTrack = null;
        }


    }


    /**
     * 执行播放语音的动作
     */
    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
        playAudio();
    }

    /**
     * 结束播放
     */
    public  void finishVoicePlayer(){
        isStopReceiver=true;
        udp.socketErrorFinish();
    }


}
