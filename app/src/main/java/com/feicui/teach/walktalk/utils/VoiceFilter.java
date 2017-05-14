package com.feicui.teach.walktalk.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.LinkedList;

public class VoiceFilter {

	private static LinkedList<String> mFilterList;

	//刷新本地的IP地址，因为这有可能发生变化
	public static void refreshFilterList() {
		String localIP = CommUtils.getLocalIP();
		if(!TextUtils.isEmpty(localIP)) {
			if(mFilterList == null) {
				mFilterList = new LinkedList<String>();
			} else {
				mFilterList.clear();
			}
			mFilterList.add(localIP);
		}
	}

	//如果这个ip地址在里面
	public static boolean isInFilterList(String ipStr) {
		if(TextUtils.isEmpty(ipStr)) {
			return false;
		}
		Log.i("Filter", "这个IP是否在过滤列表中" + ipStr);
		return mFilterList.contains(ipStr);
	}
}
