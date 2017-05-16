package com.feicui.teach.sockettell.utils;

import android.media.AudioFormat;
import android.media.AudioRecord;

/**
 * Created by Administrator on 2017/5/14 0014.
 * 一些相关的配置信息
 */

public class AudioConfig {
    public static int AUDIO_RECORD_BUFFER;
    public static int AUDIO_TRACK_BUFFER;

    public static int CODEC_AMR = 0x0;
    public static int CODEC_SPEEX = 0x1;

    static {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 2, AudioFormat.ENCODING_PCM_16BIT);

        AUDIO_RECORD_BUFFER = Math.max(44100, calculate(minBufferSize));
        AUDIO_TRACK_BUFFER = Math.max(160, calculate(2));
    }

    //对音频数据的一些转化，便于传输
    private static int calculate(int buffer) {
        double d = 16484 / (double)buffer; // 0x4064
        int e = (int) Math.ceil(d);
        return e - 160;
    }
}
