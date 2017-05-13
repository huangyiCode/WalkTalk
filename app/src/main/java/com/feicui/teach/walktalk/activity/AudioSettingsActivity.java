package com.feicui.teach.walktalk.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;
import com.feicui.teach.walktalk.dialog.CodeRateDialog;
import com.feicui.teach.walktalk.dialog.TalkTypeDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huangYi on 2017/5/12 0012.
 * 音频设置界面
 */

public class AudioSettingsActivity extends BaseActivity implements CodeRateDialog.OnCodeRateChooseResultListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_audio_settings_use_review)
    CheckBox mCbReView;

    @BindView(R.id.cb_audio_settings_use_self_code)
    CheckBox mCbUseSelf;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_audio_settings,"对讲机");
    }

    @Override
    protected void initView() {
      mCbReView.setOnCheckedChangeListener(this);
      mCbUseSelf.setOnCheckedChangeListener(this);
    }

    @OnClick(R.id.ll_audio_settings_code_rate)
    void  viewEvent(){
        CodeRateDialog dialog=new CodeRateDialog(this,R.layout.dialog_code_rate);
        dialog.setOnCodeRateChooseResultListener(this);
        dialog.show();
    }

    @Override
    public void onCodeChooseResult(double choseCode) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       switch (buttonView.getId()){
           case R.id.cb_audio_settings_use_review://回显
               if(isChecked){
                   //回显
               }else{

               }
               break;
           case R.id.cb_audio_settings_use_self_code://使用自身
               if(isChecked){
                   //使用自身
               }else{

               }
               break;
       }
    }
}
