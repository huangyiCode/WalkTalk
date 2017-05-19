package com.feicui.teach.sockettell.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.feicui.teach.sockettell.BaseActivity;
import com.feicui.teach.sockettell.utils.SystemSettings;
import com.zhaoerdragon.sockettell.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.edt_login_name)
    EditText mIvLoginName;

    @BindView(R.id.iv_login_choose_pic)
    ImageView  mIvPhoto;

    /**
     * 弹框
     */
    PopupWindow popupWindow;

    /**
     * 展示视图
     */
    View mView;

    /**
     * 跳转相机的请求码
     */
    static final int OPEN_CAMERA = 201;
    /**
     * 打开图库的请求码
     */
    static final int OPEN_PICK = 202;
    /**
     * 通过打开相机拍到的图片存储文件夹路径
     */
    public static final String DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + //根路径
            File.separator + "DailyNews";//分隔符  和文件夹名
    /**
     * 图片存储路径
     */
    public static final String PHOTO_FILE_PATH = DIR_PATH + File.separator + "myportraits.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        //使用布局填充器 将popup布局加载进来
        mView = getLayoutInflater().inflate(R.layout.view_myaccount_portraits_pop, null);
        mView.findViewById(R.id.ll_call_camera).setOnClickListener(this);//调用相机
        mView.findViewById(R.id.ll_select_photo).setOnClickListener(this);
    }

    @OnClick({R.id.btn_login_login,R.id.iv_login_choose_pic})
    void gotoHome(View v){
        switch (v.getId()){
            case R.id.btn_login_login://登陆
                SystemSettings.USER_NAME=mIvLoginName.getText().toString();
                openActivity(HomeActivity.class);
                finish();
                break;
            case R.id.iv_login_choose_pic:
                //图片选取
              popView();
                break;
        }
    }




    public  void  popView(){
        popupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //2----设置外部可点击：
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //3----进行展示
        //1.基于某一个控件去显示PopWindow
//                popupWindow.showAsDropDown(mView,0,0);//三个参数 一个View  x，y偏移量
        //2.基于屏幕取显示PopWindow
        popupWindow.showAtLocation(mIvLoginName, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_call_camera://打开相机
                popupWindow.dismiss();//关闭popupWindow
                    goToCamear();//直接调用跳转相机的方法
                break;
            case R.id.ll_select_photo://选择图库
                popupWindow.dismiss();//关闭popupWindow
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, OPEN_PICK);//有结果的跳转
                Toast.makeText(this, "请选择照片", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    /**
     * 跳转系统相机
     */
    public void goToCamear() {
        //MediaStore.ACTION_IMAGE_CAPTURE 相当于action
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转打开相机
        //传递此次拍照图片存储的路径
        //指定一个路径去存放 图片   手机SD卡路径
        File fileDir = new File(DIR_PATH);
        if (!fileDir.exists()) {//如果不存在
            fileDir.mkdirs();//创建此文件夹以及父类文件夹
        }
        File file = new File(PHOTO_FILE_PATH);
        //向相机传递文件路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, OPEN_CAMERA);//开始跳转  OPEN_CAMERA 跳转相机请求码
    }

    /**
     * 拿到回传数据的结果
     *
     * @param requestCode 请求码   区分返回结果的 请求
     * @param resultCode  结果码   区分结果是否成功
     * @param data        回传的数据数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case OPEN_CAMERA://请求相机的结果
                Toast.makeText(this, "requestCode=" + requestCode + "--resultCode==" + resultCode, Toast.LENGTH_SHORT).show();
                if (resultCode == RESULT_OK) {
                    //读取图片  路径
                    Bitmap bitmap = BitmapFactory.decodeFile(PHOTO_FILE_PATH);//通过路径拿到图片
                    SystemSettings.BITMAP=bitmap;
                    mIvPhoto.setImageBitmap(bitmap);//将图片展示出来
                }
                break;
            case OPEN_PICK://选择图库拿到的结果
                if (resultCode == RESULT_OK) {  //如果有数据
                    Uri uri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String path = cursor.getString(columIndex);
                    Log.e("aaa", "onActivityResult: " + path);
                    try {
                        Bitmap bit = BitmapFactory.decodeStream(new FileInputStream(path));
                        SystemSettings.BITMAP=bit;
                        mIvPhoto.setImageBitmap(bit);//将拿到的图片展示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
