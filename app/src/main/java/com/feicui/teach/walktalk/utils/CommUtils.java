package com.feicui.teach.walktalk.utils;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;

/**
 * 一些会用到的公共方法
 */
public class CommUtils {

    //获得本地的IP地址，如果没有的话则获取失败
	public static String getLocalIP(){
    	String ip;
	    try {
	         Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ip = inetAddress.getHostAddress())) {
						return ip;
					}
	            }
	        }
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "获取失败";
    }

    //获得本地的端口号
	public static String getLocalPort() {
		String str = getLocalIP();
		str = str.substring(str.lastIndexOf(".") + 1);
		Random r = new Random();
		String port = "";
		switch (str.length()) {
		case 1:
			for (int i = 0; i < 3; i++) {
				port += r.nextInt(9);
			}
			port += str;
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				port += r.nextInt(9);
			}
			port += str;
			break;
		case 3:
			for (int i = 0; i < 1; i++) {
				port += r.nextInt(9);
			}
			port += str;
			break;
		}
		return port;
	}
}
