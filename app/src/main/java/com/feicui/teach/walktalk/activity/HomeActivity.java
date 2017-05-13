package com.feicui.teach.walktalk.activity;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;
import com.feicui.teach.walktalk.dialog.BaseDialog;
import com.feicui.teach.walktalk.dialog.SpeakingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements View.OnTouchListener {

    @BindView(R.id.btn_home_stop_talk)
    Button mBtnStopTalk;

    SpeakingDialog mSpeakingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_home,"对讲机");
    }

    @Override
    protected void initView() {
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setText("使用说明");
        /**
         * 初始化设置
         */
        //媒体的声音就是App的声音
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mBtnStopTalk.setOnTouchListener(this);
        mSpeakingDialog=new SpeakingDialog(this,R.layout.dialog_speaking);
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

    //当返回键被点击时的事件
    long touchTime = 0;
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= 2000) {
            Toast.makeText(this, "再按一次退出本App", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            finish();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://开始录音
                mSpeakingDialog.show();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL://录音结束
                mSpeakingDialog.cancel();

                break;
        }
        return true;
    }
}
