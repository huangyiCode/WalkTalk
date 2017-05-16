package com.feicui.teach.sockettell.activity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.feicui.teach.sockettell.BaseActivity;
import com.feicui.teach.sockettell.dialog.CodeRateDialog;
import com.feicui.teach.sockettell.utils.PreferencesManager;
import com.feicui.teach.sockettell.utils.SystemSettings;
import com.zhaoerdragon.sockettell.R;

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
      mCbReView.setChecked(SystemSettings.IS_ECHO);
      mCbUseSelf.setChecked(!SystemSettings.USE_SPEEX);
    }

    @OnClick(R.id.ll_audio_settings_code_rate)
    void  viewEvent(){
        CodeRateDialog dialog=new CodeRateDialog(this,R.layout.dialog_code_rate);
        dialog.setOnCodeRateChooseResultListener(this);
        dialog.show();
    }

    @Override
    public void onCodeChooseResult(int choseIndex) {
        SystemSettings.SPEEX_QUALITY=choseIndex;
        PreferencesManager.getInstance(this).put(getResources().getString(R.string.speex_quality),choseIndex);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       switch (buttonView.getId()){
           case R.id.cb_audio_settings_use_review://回显
               SystemSettings.IS_ECHO=isChecked;
               PreferencesManager.getInstance(this).put(getResources().getString(R.string.echo),isChecked);
               break;
           case R.id.cb_audio_settings_use_self_code://使用自身
               SystemSettings.USE_SPEEX=!isChecked;
               PreferencesManager.getInstance(this).put(getResources().getString(R.string.use_speex),!isChecked);
               break;
       }
    }
}
