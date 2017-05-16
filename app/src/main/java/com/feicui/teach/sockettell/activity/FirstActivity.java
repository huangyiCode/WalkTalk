package com.feicui.teach.sockettell.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.feicui.teach.sockettell.BaseActivity;
import com.feicui.teach.sockettell.utils.BroadCastType;
import com.feicui.teach.sockettell.utils.PreferencesManager;
import com.feicui.teach.sockettell.utils.SystemSettings;
import com.zhaoerdragon.sockettell.R;

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



        initConfigParams();


    }

    /**
     * 此处需要进行数据初始化  SystemSettings 数据存储在SharedPreference
     */
    public void initConfigParams(){
        //端口号
        SystemSettings.PORT_NUMBER=PreferencesManager.getInstance(this).get(getResources().getString(R.string.port),getResources().getInteger(R.integer.default_port));
        //使用的广播类型
        SystemSettings.CAST_TYPE= BroadCastType.values()[PreferencesManager.getInstance(this).get(getResources().getString(R.string.broadcast_type),0)];
        //广播地址
        SystemSettings.BROADCAST_IP=PreferencesManager.getInstance(this).get(BroadCastType.BROADCAST_ADDRESS.name(),getResources().getString(R.string.default_broadcast_ip));
        //组播地址
        SystemSettings.MULTICAST_IP=PreferencesManager.getInstance(this).get(BroadCastType.BROADCAST_GROUP_ADDRESS.name(),getResources().getString(R.string.default_group_broadcast_ip));
        //广播地址
        SystemSettings.UNICAST_IP=PreferencesManager.getInstance(this).get(BroadCastType.BROADCAST_SINGLE_ADDRESS.name(),getResources().getString(R.string.default_single_broadcast_ip));
        //是否使用Speex解码
        SystemSettings.USE_SPEEX=PreferencesManager.getInstance(this).get(getResources().getString(R.string.use_speex),false);
        //Speex的品质
        SystemSettings.SPEEX_QUALITY=PreferencesManager.getInstance(this).get(getResources().getString(R.string.speex_quality),getResources().getIntArray(R.array.speex_quality_values)[0]);
        //是否回显
        SystemSettings.IS_ECHO=PreferencesManager.getInstance(this).get(getResources().getString(R.string.echo),false);
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
