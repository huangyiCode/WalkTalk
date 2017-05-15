package com.feicui.teach.walktalk.utils;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;

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

    public VoicePlayer(Context context){
        mContext=context;
    }


    /**
     * 播放录音数据
     */
    public void playAudio(){
        /**
         * 初始化Socket
         */
        try {
             udp=new UDPUtil();
            udp.initSocketReceiverData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         *初始化播放器
         */
        initAudioTrack();

        // 刷新过滤列表, 本机IP可能改变
        VoiceFilter.refreshFilterList();
        /**
         * 初始化接收数据包
         */
        byte[] byteBuffer=null;
        if(SystemSettings.USE_SPEEX) {
            byteBuffer = new byte[320];

        } else {
            byteBuffer = new byte[320];
        }

        udp.packReceiverData(byteBuffer);

        Log.e("aaaa", "mAudioTrack=="+"执行run方法"+isStopReceiver );


        /**
         * 循环接使用UDP接收信息并且播放
         */
        while(!isStopReceiver) {

            udp.getVoiceData();
            //用AudioTrack来播放声音
            Log.e("aaaa", "mAudioTrack=="+mAudioTrack );
//            Toast.makeText(mContext, "--接收到信息--", Toast.LENGTH_SHORT).show();
            if(mAudioTrack!=null){//防止线程安全问题
                if (SystemSettings.USE_SPEEX) {
                    mAudioTrack.write(byteBuffer, 0, 320);
                } else {
                    Log.e("aaaa", "playAudio: mAudioTrack="+mAudioTrack+"isStopReceiver="+isStopReceiver );
                    Log.e("bbbbb", "playAudio: "+byteBuffer );
                    mAudioTrack.write(byteBuffer, 0, 320);
                }
            }

        }
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

        if(mAudioTrack != null) {
            if(mAudioTrack.getPlayState() != AudioTrack.PLAYSTATE_STOPPED) {
                mAudioTrack.stop();
            }

        }
        mAudioTrack.release();
        mAudioTrack = null;
        udp.releaseSocket();
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
        releaseAudioTrack();
    }


}
