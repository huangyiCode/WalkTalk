package com.feicui.teach.sockettell.dialog;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.feicui.teach.sockettell.utils.PreferencesManager;
import com.zhaoerdragon.sockettell.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class CodeRateDialog extends BaseDialog implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.ll_dialog_code_rate_content)
    LinearLayout mLLContent;
    
    @BindView(R.id.sv_dialog_code_rate_content)
    ScrollView mSVContent;

    @BindView(R.id.ll_dialog_code_rate_scroll_content)
    LinearLayout mScrollContent;

    ViewGroup mViewContent;

    LayoutInflater mInflate;


    public  CodeRateDialog(Context context, int layoutId) {
        super(context, layoutId);
        mInflate= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
    }

    private void init() {
        mSVContent.setVisibility(mContext.getResources().getStringArray(R.array.speex_quality).length>8? View.VISIBLE:View.GONE);
        mLLContent.setVisibility(mContext.getResources().getStringArray(R.array.speex_quality).length<=8? View.VISIBLE:View.GONE);
        mViewContent= mContext.getResources().getStringArray(R.array.speex_quality).length>8?mScrollContent:mLLContent;
        setContent();
    }

    private void setContent(){
        for (int i = 0; i <mContext.getResources().getStringArray(R.array.speex_quality).length ; i++) {
            View view=mInflate.inflate(R.layout.item_code_rate,null);
            mViewContent.addView(view);
            TextView mTvCode= (TextView) view.findViewById(R.id.tv_item_code_rate_code);
            CheckBox mCbCode= (CheckBox) view.findViewById(R.id.cb_item_code_rate_code);
            mTvCode.setText(mContext.getResources().getStringArray(R.array.speex_quality)[i]+"kbps");
            Log.e("aaa", "setContent: "+mContext.getResources().getStringArray(R.array.speex_quality)[i]);
            mCbCode.setOnCheckedChangeListener(this);
            if(PreferencesManager.getInstance(mContext).get(mContext.getResources().getString(R.string.speex_quality),0)==i){
                mCbCode.setChecked(true);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(flag){
            /**
             * 选中一个其他的都需要进行去掉状态
             */
            for (int i = 0; i <mViewContent.getChildCount() ; i++) {
                CheckBox box= (CheckBox) mViewContent.getChildAt(i).findViewById(R.id.cb_item_code_rate_code);
                    flag=false;
                    box.setChecked(false);
                    flag=true;
            }
            buttonView.setChecked(isChecked);
        }

    }

    @OnClick({R.id.btn_dialog_code_rate_cancel,R.id.btn_dialog_code_rate_sure})
    void handleEvent(View view){
     switch (view.getId()){
         case R.id.btn_dialog_code_rate_cancel://取消
             cancel();
             break;
         case R.id.btn_dialog_code_rate_sure://确定
             if(mOnCodeRateChooseResultListener!=null){
                 for (int i = 0; i <mViewContent.getChildCount() ; i++) {
                     CheckBox box= (CheckBox) mViewContent.getChildAt(i).findViewById(R.id.cb_item_code_rate_code);
                     if(box.isChecked()){
                         mOnCodeRateChooseResultListener.onCodeChooseResult(i);
                        break;
                     }
                 }
             }
             cancel();
             break;
     }
    }

    boolean flag=true;

    OnCodeRateChooseResultListener mOnCodeRateChooseResultListener;

    public void setOnCodeRateChooseResultListener(OnCodeRateChooseResultListener mOnCodeRateChooseResultListener) {
        this.mOnCodeRateChooseResultListener = mOnCodeRateChooseResultListener;
    }

    public interface OnCodeRateChooseResultListener{

        void onCodeChooseResult(int choseIndex);

    }
}
