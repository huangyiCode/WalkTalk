package com.feicui.teach.sockettell.dialog;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.feicui.teach.sockettell.utils.SystemSettings;
import com.zhaoerdragon.sockettell.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class PortDialog extends BaseDialog implements TextWatcher {

    @BindView(R.id.tl_port_input)
    TextInputLayout mTlPort;
    @BindView(R.id.te_port_input)
    TextInputEditText mTePort;

    public PortDialog(Context context, int layoutId) {
        super(context, layoutId);
        init();
    }

    public void init(){
        mTePort.setText(SystemSettings.PORT_NUMBER+"");
        mTePort.setSelection(mTePort.getText().length());
        mTePort.addTextChangedListener(this);
    }

    @OnClick({R.id.btn_port_cancel,R.id.btn_port_sure})
    void handleEvent(View view){
        switch (view.getId()){
            case R.id.btn_port_cancel://取消
                cancel();
                break;
            case R.id.btn_port_sure://确定
                if(mOnPortDialogFinishResultListener!=null&&mTePort.getText().length()>0&&mTlPort.getError()==null){
                    int port=Integer.parseInt(mTePort.getText().toString());
                    mOnPortDialogFinishResultListener.onPortResult(port);
                    cancel();
                }

                break;
        }
    }

    OnPortDialogFinishResultListener mOnPortDialogFinishResultListener;

    public void setOnPortDialogFinishResultListener(OnPortDialogFinishResultListener mOnPortDialogFinishResultListener) {
        this.mOnPortDialogFinishResultListener = mOnPortDialogFinishResultListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mTlPort.setError(s.length()>0?null:"请输入正确的端口号");
    }

    /**
     * 回调接口传出选择结果
     */
    public interface OnPortDialogFinishResultListener {
        void onPortResult(int port);
    }
}
