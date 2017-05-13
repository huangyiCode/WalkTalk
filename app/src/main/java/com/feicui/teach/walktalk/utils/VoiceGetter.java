package com.feicui.teach.walktalk.utils;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Config;

/**
 * Created by Administrator on 2017/5/14 0014.
 * 录音工具类
 */

public class VoiceGetter {

    /**
     * 录音器
     */
    public  AudioRecord mAudioRecord;

    /**
     * 数据缓存
     */
    public  byte[] mBuffer=new byte[320];

    /**
     * 是否暂停数据读取
     */
    public  boolean isStop;


    /**
     * 开始录音
     */
    public  void startGetterVoice(){
        isStop=false;
        initAudioRecord();
        //录音器开始录音
       mAudioRecord.startRecording();

        /**
         * 此处需要判断是否使用系统的编码解码方式进行处理
         */
        while (!isStop){
            if(SystemSettings.USE_SPEEX){//Speex

            }else{//系统
                mAudioRecord.read(mBuffer,0,320);
            }
        }
    }

    /**
     * 结束录音
     */
    public  void finishGetterVoice(){
        isStop=true;
        releaseAudioRecord();
    }

    /**
     * 初始化音频播放
     */
    public void initAudioRecord(){
        mAudioRecord=new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, /*AudioFormat.CHANNEL_IN_STEREO*/2, AudioFormat.ENCODING_PCM_16BIT, AudioConfig.AUDIO_RECORD_BUFFER);
    }

    /**
     * 释放资源
     */
    public void releaseAudioRecord(){
        mAudioRecord.release();
    }
}
