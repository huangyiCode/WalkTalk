package com.feicui.teach.walktalk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * create by huangYi 主界面
 */
public class HomeActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {

    }


    /**
     * 跳转
     */
    @OnClick(R.id.btn_home_start) void gotoNext(){
//        openActivity();
    }

}
