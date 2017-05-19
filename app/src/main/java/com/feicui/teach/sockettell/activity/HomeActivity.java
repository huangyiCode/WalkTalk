package com.feicui.teach.sockettell.activity;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feicui.teach.sockettell.BaseActivity;
import com.feicui.teach.sockettell.dialog.SpeakingDialog;
import com.feicui.teach.sockettell.utils.CommUtils;
import com.feicui.teach.sockettell.utils.SystemSettings;
import com.feicui.teach.sockettell.utils.VoiceGetter;
import com.feicui.teach.sockettell.utils.VoicePlayer;
import com.zhaoerdragon.sockettell.R;

import butterknife.BindView;
import butterknife.OnClick;
import org.lansir.ip.codecs.Speex;

public class HomeActivity extends BaseActivity implements View.OnTouchListener {

    @BindView(R.id.btn_home_stop_talk)
    Button mBtnStopTalk;

    @BindView(R.id.tv_home_current_ip)
    TextView mTvIpString;

    @BindView(R.id.tv_home_current_port)
    TextView mTvPortString;

    @BindView(R.id.tv_home_point_ip)
     TextView mTvPointIp;

    @BindView(R.id.tv_home_talk_type)
     TextView mTvTalkType;

    @BindView(R.id.ll_home_record)
    LinearLayout mLLRecord;

    @BindView(R.id.iv_home_photo)
    ImageView mIvPhoto;

    @BindView(R.id.tv_home_name)
    TextView mTvName;

    /**
     * 通话中
     */
    SpeakingDialog mSpeakingDialog;

    /**
     * 录音
     */
    VoiceGetter mVoiceGetter;

    /**
     * 播放
     *
     */
    VoicePlayer mVoicePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_home,"智能对讲机");
    }

    @Override
    protected void initView() {
        mTvTitleRight.setVisibility(View.VISIBLE);
        mTvTitleRight.setText("使用说明");
        if(SystemSettings.BITMAP!=null){
            mIvPhoto.setImageBitmap(SystemSettings.BITMAP);
        }
        mTvName.setText(SystemSettings.USER_NAME);
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
                startGetVoice();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL://录音结束
                stopGetVoice();
                break;
        }
        return true;
    }

    /**
     * 开始录音
     */
    public void startGetVoice(){
        mSpeakingDialog.show();
        mVoiceGetter=new VoiceGetter();
        mVoiceGetter.start();
    }

    /**
     * 结束录音
     */
    public void stopGetVoice(){
        mSpeakingDialog.cancel();
        mVoiceGetter.finishGetterVoice();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 开始接收别人传递过来的数据信息
         */
        mVoicePlayer=new VoicePlayer(this);
        mVoicePlayer.start();
        refreshIPInfo();
    }


    /**
     * 刷新Ip信息
     */
    public void  refreshIPInfo(){
        mTvIpString.setText(CommUtils.getLocalIP());
        mTvPortString.setText(SystemSettings.PORT_NUMBER+"");
        mTvTalkType.setText(SystemSettings.CAST_TYPE.getTypeName());
        mTvPointIp.setText(SystemSettings.CAST_TYPE.getTypeAddress());
    }

    @Override
    protected void onStop() {
        super.onStop();
        /**
         * 停止接收他人传递的信息
         */
        //关闭VoicePlayer
        if(mVoicePlayer != null) {
            mVoicePlayer.finishVoicePlayer();
            mVoicePlayer = null;
        }
        Log.e("aaa", "CPU架构:"+Build.CPU_ABI );
        //关闭解码
        if(Build.CPU_ABI.equals("armeabi")||Build.CPU_ABI.equals("x86")){
           Speex.close();
        }
    }


    /**
     * 添加语音回放记录  (本地)
     */
    public void addAudioRecord(){

    }
}
