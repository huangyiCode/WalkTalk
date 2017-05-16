package org.lansir.ip.codecs;

/**
 * 保持不变
 * @author org.lansir.ip
 */
public class Speex {

	private static final int[] mCompressionValues;

	static {
		System.loadLibrary("speex_jni");
		mCompressionValues = new int[] { 6, 10, 15, 20, 20, 28, 28, 38, 38, 46, 62 };
	}

	//得到编码的规则
	public static int getCompressionValue(int speexQualatyValue) {
		return mCompressionValues[speexQualatyValue];
	}
	//关闭
	public static native void close();
	//解码
    public static native int decode(byte[] decodeData, int offSet, short[] resultData);
    //编码
	public static native int encode(short[] encodeData, byte[] resultData);
	//打开
    public static native void open(int compressionValue);
}
