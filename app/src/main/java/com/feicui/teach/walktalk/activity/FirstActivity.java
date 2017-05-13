package com.feicui.teach.walktalk.activity;

import android.os.Bundle;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;

import butterknife.OnClick;

/**
 * create by huangYi 主界面
 */
public class FirstActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void initView() {

    }


    /**
     * 跳转
     */
    @OnClick(R.id.btn_home_start) void gotoNext(){
        openActivity(HomeActivity.class);
        finish();
    }

}
