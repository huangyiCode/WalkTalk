package com.feicui.teach.sockettell.dialog;

import android.content.Context;
import android.widget.Button;

import com.zhaoerdragon.sockettell.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class FirstDialog extends BaseDialog {


    public FirstDialog(Context context, int layoutId) {
        super(context, layoutId);
    }

    @OnClick(R.id.btn_first_goto) void gotoNext(){
        dismiss();
        if(mOnFirstDialogFinish!=null){
            mOnFirstDialogFinish.onFinish();
        }
    }

    OnFirstDialogFinish mOnFirstDialogFinish;

    public void setOnFirstDialogFinish(OnFirstDialogFinish mOnFirstDialogFinish) {
        this.mOnFirstDialogFinish = mOnFirstDialogFinish;
    }

    public  interface OnFirstDialogFinish{
        void  onFinish();
    }
}
