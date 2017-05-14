package com.feicui.teach.walktalk.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
        /**
         * 需要申请6.0权限
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(PackageManager.PERMISSION_GRANTED==checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            }else{
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }
        }

    }


    /**
     * 跳转
     */
    @OnClick(R.id.btn_home_start) void gotoNext(){
        openActivity(HomeActivity.class);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }else{
            Toast.makeText(mContext, "需要同意此权限!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
