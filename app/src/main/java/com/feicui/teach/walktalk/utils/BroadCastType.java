package com.feicui.teach.walktalk.utils;

/**
 * Created by huangYi on 2017/5/14 0014.
 *
 * 广播类型
 */

public enum  BroadCastType {
    BROADCAST_ADDRESS {
        @Override
        public String getTypeName() {
            return "广播";
        }

        @Override
        public String getTypeAddress() {
            return SystemSettings.BROADCAST_IP;
        }
    },BROADCAST_GROUP_ADDRESS {
        @Override
        public  String getTypeName() {
            return "组播";
        }

        @Override
        public String getTypeAddress() {
            return SystemSettings.MULTICAST_IP;
        }
    },BROADCAST_SINGLE_ADDRESS {
        @Override
        public String getTypeName() {
            return "单播";
        }

        @Override
        public String getTypeAddress() {
            return SystemSettings.UNICAST_IP;
        }
    };


    public abstract String getTypeName();

    public abstract String getTypeAddress();

}
