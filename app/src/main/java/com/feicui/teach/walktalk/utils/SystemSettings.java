package com.feicui.teach.walktalk.utils;

/**
 * Created by Administrator on 2017/5/14 0014.
 * 用户的一些设置
 */

public class SystemSettings {

    /**
     * 是否使用Speex编码
     */
    public static boolean USE_SPEEX=false;

    /**
     * 使用的广播类型
     */
    public static BroadCastType CAST_TYPE=BroadCastType.BROADCAST_ADDRESS;

    /**
     * 广播地址
     */
    public static String BROADCAST_IP="";

    /**
     * 组播地址
     */
    public static String MULTICAST_IP="";

    /**
     * 单播地址
     */
    public  static String UNICAST_IP="";

    /**
     * 端口号
     */
    public static int PORT;
}
