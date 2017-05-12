package com.feicui.teach.walktalk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;

import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_home,"对讲机");
    }

    @Override
    protected void initView() {
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setText("使用说明");
    }

    @OnClick({R.id.btn_home_audio,R.id.btn_home_talk_type,R.id.tv_title_right})
    void goToNext(View v){
        switch (v.getId()){
            case R.id.btn_home_audio://音频
                openActivity(AudioSettingsActivity.class);
                break;
            case R.id.btn_home_talk_type://对讲方式
                openActivity(TalkTypeActivity.class);
                break;
            case R.id.tv_title_right://说明
                openActivity(UseInfoActivity.class);
                break;
        }
    }
}
