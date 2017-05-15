package com.feicui.teach.walktalk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.feicui.teach.walktalk.BaseActivity;
import com.feicui.teach.walktalk.R;

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
