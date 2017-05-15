package com.feicui.teach.walktalk.utils;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Process;
import java.io.IOException;

import codecs.Speex;

/**
 * Created by Administrator on 2017/5/14 0014.
 * 录音工具类
 */

public class VoiceGetter extends Thread {

    /**
     * 录音器
     */
    private   AudioRecord mAudioRecord;

    /**
     * 数据缓存
     */
    private  byte[] mBuffer;
    private short[] mShortBuffer = new short[' '];

    /**
     * 是否暂停数据读取
     */
    private  boolean isStop;

    UDPUtil udp;


    /**
     * 开始录音
     */
    private  void startGetterVoice() {
        initAudioRecord();

        /**
         * 初始化Socket
         */
        try {
            udp=new UDPUtil();
            udp.initSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 初始化缓存数据
         */
        if (SystemSettings.USE_SPEEX) {//Speex
            mBuffer = new byte[Speex.getCompressionValue(SystemSettings.SPEEX_QUALITY)];
        } else {//系统
            mBuffer = new byte[320];

        }


        /**
         * 封装数据包
         */
        udp.packSendData(mBuffer);


        /**
         * 此处需要不停的获取数据----->传输数据
         */
        while (!isStop) {
            /**
             * 此处需要判断是否使用系统的编码解码方式进行处理
             *   读取录音信息
             */
            int len = 0;
            if (SystemSettings.USE_SPEEX) {//Speex
                //读取录音器的数据到mShortBuffer中
                mAudioRecord.read(mShortBuffer, 0, 160);
                //将mShortBuffer中的数据进行编码，便于发送
                Speex.encode(mShortBuffer, mBuffer);
            } else {//系统
                len = mAudioRecord.read(mBuffer, 0, 320);
            }

            /**
             * 读取到录音结果之后需要将声音使用UDP进行传输
             */

            udp.sendVoiceData();
        }


        releaseAudioRecord();

    }



        /**
     * 结束录音
     */
    public  void finishGetterVoice(){
        isStop=true;
    }

    /**
     * 初始化音频播放
     */
    private void initAudioRecord(){
        mAudioRecord=new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, /*AudioFormat.CHANNEL_IN_STEREO*/2, AudioFormat.ENCODING_PCM_16BIT, AudioConfig.AUDIO_RECORD_BUFFER);
        //录音器开始录音
        mAudioRecord.startRecording();
    }

    /**
     * 释放资源
     */
    private void releaseAudioRecord(){
        //关闭Socket
        udp.releaseSocket();
        //关闭录音机器
        mAudioRecord.release();

    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
        startGetterVoice();
    }
}
