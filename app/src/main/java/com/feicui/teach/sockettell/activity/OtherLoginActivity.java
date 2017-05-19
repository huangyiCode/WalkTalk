package com.feicui.teach.sockettell.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.feicui.teach.sockettell.BaseActivity;
import com.zhaoerdragon.sockettell.R;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class OtherLoginActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn_login_register) void gotoNext(){
         openActivity(FirstActivity.class);
    }
}
