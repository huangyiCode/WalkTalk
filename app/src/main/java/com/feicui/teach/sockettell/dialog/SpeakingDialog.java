package com.feicui.teach.sockettell.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zhaoerdragon.sockettell.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class SpeakingDialog extends BaseDialog {

    @BindView(R.id.tv_dialog_speak)
    TextView mTvSpeak;

    MyHandler handler=new MyHandler(this);

    public SpeakingDialog(Context context, int layoutId) {
        super(context, layoutId);
        init();
    }

    private void init() {


    }

    public void refresh(Message message){
        builder.delete(0,builder.length());
        builder.append("通话中");
        for (int i = 0; i <message.arg1%4; i++) {
            builder.append(".");
        }
        mTvSpeak.setText(builder.toString());
        Message msg=Message.obtain();
        msg.arg1=message.arg1+1;
        handler.sendMessageDelayed(msg,500);
    }

    @Override
    public void show() {
        super.show();
        Message message=Message.obtain();
        message.arg1=0;
        handler.sendMessage(message);
    }

    @Override
    public void cancel() {
        handler.removeCallbacksAndMessages(null);
        super.cancel();
    }

    private StringBuilder builder=new StringBuilder("");


    public static class MyHandler extends Handler{

        WeakReference<SpeakingDialog> mDialog;

        public  MyHandler(SpeakingDialog dialog){
            mDialog=new WeakReference<SpeakingDialog>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            mDialog.get().refresh(msg);
        }
    }
}
