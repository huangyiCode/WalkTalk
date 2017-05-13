package com.feicui.teach.walktalk.dialog;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.feicui.teach.walktalk.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/13 0013.
 * 广播地址输入
 */

public class BroadcastAddressInputDialog extends BaseDialog implements TextWatcher {

    @BindView(R.id.tl_broadcast_address_input)
    TextInputLayout mTlBroadcastAddress;


    @BindView(R.id.te_broadcast_address_input)
    TextInputEditText   mTeBroadcastAddress;

    @BindView(R.id.tv_broadcast_type)
    TextView mTvBroadCaseType;

    /**
     * 广播类型
     */
    BroadCastType type;



    /**
     *
     * @param context
     * @param layoutId
     * @param type  广播地址类型
     */
    public BroadcastAddressInputDialog(Context context, int layoutId,BroadCastType type) {
        super(context, layoutId);
        this.type=type;
        init();
    }

    private void init() {
        mTeBroadcastAddress.addTextChangedListener(this);
        mTvBroadCaseType.setText(type.getTypeName());
        mTeBroadcastAddress.setHint(type.getTypeName());
    }

    @OnClick({R.id.btn_broadcast_address_cancel,R.id.btn_broadcast_address_sure})
    void handleEvent(View view){
        switch (view.getId()){
            case R.id.btn_broadcast_address_cancel://取消
                cancel();
                break;
            case R.id.btn_broadcast_address_sure://确定
               if(mOnBroadcastInputDialogFinishResultListener!=null&&mTlBroadcastAddress.getError().length()==0&&mTeBroadcastAddress.getText().length()>0){
                 mOnBroadcastInputDialogFinishResultListener.onBroadcastInputResult(mTeBroadcastAddress.getText().toString(),type);
                   cancel();
               }

                break;
        }
    }

    OnBroadcastInputDialogFinishResultListener mOnBroadcastInputDialogFinishResultListener;

    public void setOnBroadcastInputDialogFinishResultListener(OnBroadcastInputDialogFinishResultListener mOnBroadcastInputDialogFinishResultListener) {
        this.mOnBroadcastInputDialogFinishResultListener = mOnBroadcastInputDialogFinishResultListener;
    }

    public interface OnBroadcastInputDialogFinishResultListener{
        void onBroadcastInputResult(String address,BroadCastType type);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
      if(s.length()>0){
         if(true){//格式判断
             mTlBroadcastAddress.setError("");
         }else{
             mTlBroadcastAddress.setError(type.getTypeName()+"格式不正确");
         }
      }else{
          mTlBroadcastAddress.setError("请输入"+type.getTypeName());
      }
    }

    /**
     * 广播类型
     */
    public enum  BroadCastType{
      BROADCAST_ADDRESS {
          @Override
          String getTypeName() {
              return "广播地址";
          }
      },BROADCAST_GROUP_ADDRESS {
            @Override
            String getTypeName() {
                return "组播地址";
            }
        },BROADCAST_SINGLE_ADDRESS {
            @Override
            String getTypeName() {
                return "单播地址";
            }
        };


        abstract String getTypeName();

    }

}
