package com.feicui.teach.sockettell.activity;

import android.os.Bundle;
import android.view.View;

import com.feicui.teach.sockettell.BaseActivity;
import com.feicui.teach.sockettell.dialog.BroadcastAddressInputDialog;
import com.feicui.teach.sockettell.dialog.PortDialog;
import com.feicui.teach.sockettell.dialog.TalkTypeDialog;
import com.feicui.teach.sockettell.utils.BroadCastType;
import com.feicui.teach.sockettell.utils.PreferencesManager;
import com.feicui.teach.sockettell.utils.SystemSettings;
import com.zhaoerdragon.sockettell.R;

import butterknife.OnClick;

/**
 * 对讲方式
 */
public class TalkTypeActivity extends BaseActivity implements TalkTypeDialog.OnTalkTypeDialogFinishResultListener, PortDialog.OnPortDialogFinishResultListener, BroadcastAddressInputDialog.OnBroadcastInputDialogFinishResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_talk_type,"对讲机");
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.ll_talk_type_talk_type,R.id.ll_talk_type_port,R.id.ll_talk_type_broadcast_address
             ,R.id.ll_talk_type_group_broadcast_address,R.id.ll_talk_type_single_broadcast_address})
    void clickShow(View view){
        switch (view.getId()){
            case R.id.ll_talk_type_talk_type://对讲类型
                TalkTypeDialog dialog=new TalkTypeDialog(this, R.layout.dialog_talk_type);
                dialog.setOnTalkTypeDialogFinishResultListener(this);
                dialog.show();
                break;
            case R.id.ll_talk_type_port://端口
                PortDialog port=new PortDialog(this,R.layout.dialog_port);
                port.setOnPortDialogFinishResultListener(this);
                port.show();
                break;
            case R.id.ll_talk_type_broadcast_address://广播地址
                BroadcastAddressInputDialog broadAddress=new BroadcastAddressInputDialog(this,R.layout.dialog_broad_address, BroadCastType.BROADCAST_ADDRESS);
                broadAddress.setOnBroadcastInputDialogFinishResultListener(this);
                broadAddress.show();
                break;
            case R.id.ll_talk_type_group_broadcast_address://组播地址
                BroadcastAddressInputDialog groupAddress=new BroadcastAddressInputDialog(this,R.layout.dialog_broad_address, BroadCastType.BROADCAST_GROUP_ADDRESS);
                groupAddress.setOnBroadcastInputDialogFinishResultListener(this);
                groupAddress.show();
                break;
            case R.id.ll_talk_type_single_broadcast_address://单播地址
                BroadcastAddressInputDialog singleAddress=new BroadcastAddressInputDialog(this,R.layout.dialog_broad_address, BroadCastType.BROADCAST_SINGLE_ADDRESS);
                singleAddress.setOnBroadcastInputDialogFinishResultListener(this);
                singleAddress.show();
                break;
        }
    }

    /**
     * 对讲类型的选择结果
     * @param index  对讲类型选择的下标
     */
    @Override
    public void onTalkTypeResult(int index) {
        PreferencesManager.getInstance(this).put(getResources().getString(R.string.broadcast_type),index);
        SystemSettings.CAST_TYPE=BroadCastType.values()[index];
    }

    /**
     * 端口号的选择结果
     * @param port
     */
    @Override
    public void onPortResult(int port) {
       SystemSettings.PORT_NUMBER=port;
        PreferencesManager.getInstance(this).put(getResources().getString(R.string.port),port);
    }

    /**
     * 广播地址的输入结果
     * @param address
     * @param type 广播类型
     */
    @Override
    public void onBroadcastInputResult(String address,BroadCastType type) {
       switch (type){
           case BROADCAST_ADDRESS://广播
               SystemSettings.BROADCAST_IP=address;
               break;
           case BROADCAST_GROUP_ADDRESS://组播
               SystemSettings.MULTICAST_IP=address;
               break;
           case BROADCAST_SINGLE_ADDRESS://单播
               SystemSettings.UNICAST_IP=address;
               break;
       }
        PreferencesManager.getInstance(this).put(type.name(),address);
    }
}
