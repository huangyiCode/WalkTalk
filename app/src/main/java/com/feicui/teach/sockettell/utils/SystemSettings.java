package com.feicui.teach.sockettell.utils;

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
     * 使用的Speex的品质
     */
    public static int SPEEX_QUALITY=1;

    /**
     * 是否回显
     */
    public static boolean IS_ECHO;

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
    public static long PORT_NUMBER;







}
