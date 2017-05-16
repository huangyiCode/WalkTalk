package com.feicui.teach.sockettell.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.feicui.teach.sockettell.BaseActivity;
import com.zhaoerdragon.sockettell.R;

import butterknife.BindView;

public class UseInfoActivity extends BaseActivity {

    @BindView(R.id.tv_use_info)
    TextView mTvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithDefaultTitle(R.layout.activity_use_info,"使用说明");
    }

    @Override
    protected void initView() {

    }
}
