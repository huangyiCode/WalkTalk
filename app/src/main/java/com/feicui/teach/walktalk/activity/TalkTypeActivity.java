package com.feicui.teach.walktalk.activity;

import android.os.Bundle;
import android.view.View;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;
import com.feicui.teach.walktalk.dialog.BroadcastAddressInputDialog;
import com.feicui.teach.walktalk.dialog.PortDialog;
import com.feicui.teach.walktalk.dialog.TalkTypeDialog;
import com.feicui.teach.walktalk.utils.BroadCastType;

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

    }

    /**
     * 端口号的选择结果
     * @param port
     */
    @Override
    public void onPortResult(int port) {

    }

    /**
     * 广播地址的输入结果
     * @param address
     * @param type 广播类型
     */
    @Override
    public void onBroadcastInputResult(String address,BroadCastType type) {

    }
}
