package com.feicui.teach.walktalk.utils;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by Administrator on 2017/5/14 0014.
 *
 * 注意:此处需要两个不同的对象  去掉所有的static
 */

public class UDPUtil {
    //用到时网络编程方面的知识
    //使用的是UDP协议
    private  DatagramSocket mDatagramSocket;
    //组播的套接字
    private  MulticastSocket mMultiSocket;
    //数据包
    private  DatagramPacket mPacket;
    //UDP发送的地址
    private  InetAddress mInetAddress;

    /**
     * 发送网络数据
     *  @param
     */
    public  void sendVoiceData(){
        //将数据发送过去
        try {
            Log.e("aaaaa", "sendVoiceData: mDatagramSocket=="+mDatagramSocket+"mPacket=="+mPacket );
            mDatagramSocket.send(mPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收网络数据
     */
    public  void getVoiceData(){

        //IOE Exception  接收数据
        try {
            mDatagramSocket.receive(mPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        //用AudioTrack来播放声音
//        if(SystemSettings.USE_SPEEX) {
////            Speex.decode(byteBuffer, byteBuffer.length, shortBuffer);
////            mAudioTrack.write(shortBuffer, 0, 160);
//        } else {
//            mAudioTrack.write(byteBuffer, 0, 320);
//        }


    }

    /**
     * 初始化套接字
     */
    public  void initSocket() throws IOException {
        //根据不同的套接字来实现
        switch (SystemSettings.CAST_TYPE) {
            case BROADCAST_ADDRESS:// 广播
                mDatagramSocket = new DatagramSocket();
                break;
            case BROADCAST_GROUP_ADDRESS:// 组播
                mMultiSocket = new MulticastSocket();
                mDatagramSocket = mMultiSocket;
                break;
            case BROADCAST_SINGLE_ADDRESS:// 单播
                mDatagramSocket = new DatagramSocket();
                break;
        }
        mInetAddress = InetAddress.getByName(SystemSettings.CAST_TYPE.getTypeAddress());
        //设置连接时延，如果对方没有连接则断开连接
        mDatagramSocket.setSoTimeout(0);
    }

    //释放Socket的资源
    public  void releaseSocket() {
        if(mMultiSocket != null) {
            mMultiSocket = null;
        }

        if(mDatagramSocket != null) {
            mDatagramSocket.close();
            mDatagramSocket = null;
        }
    }

    /**
     * 打包发送数据
     */
    public  void packSendData(byte [] mByteBuffer){
        //准备发送的文件的信息
        mPacket = new DatagramPacket(mByteBuffer, mByteBuffer.length, mInetAddress, (int)SystemSettings.PORT_NUMBER);
    }

    /**
     * 打包接收数据
     */
    public  void packReceiverData(byte [] mByteBuffer){
        //准备发送的文件的信息
        mPacket = new DatagramPacket(mByteBuffer, mByteBuffer.length);
    }


}
