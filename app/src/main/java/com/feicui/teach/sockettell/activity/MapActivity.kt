package com.feicui.teach.sockettell.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.ButterKnife
import com.feicui.teach.sockettell.BaseActivity

/**
 * Created by Administrator on 2017/5/25 0025.
 * 展示地图界面
 *   Cotlin语法:使用 ：代替继承(extends)  并且可以在继承的时候指定实现的构造方法
 *
 *    类的声明:如果没有类体可以不包含大括号
 *
 *    构造函数:
 *       一级构造函数:在类声明的时候需要指定  可以省略constructor关键字  也可以省略方法体
 *       二级构造函数可以有多个:如果有一级构造函数需要使用this调用代理
 *         constructor(name:String) : this() {

           }
 */
 class MapActivity():BaseActivity(){

    /**
     * 二级构造函数
     */
//    constructor(name:String) : this() {
//
//    }

    /**
     * 视图展示
     *
     */
    internal var mWebView:WebView?=null

//    var stringRepresentation: String
//        get() = this.toString()
//        set (value) {
////            setDataFormString(value) // 格式化字符串,并且将值重新赋值给其他元素
//        }
//
//
//    var isEmpty: Boolean
//        get() = this==null
//       set(value) {
//
//       }
//     var a:Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWebView= WebView(this);
        setContentView(mWebView);
    }



    /**
     * 图形渲染 内容填充
     */
    override fun initView() {


        mWebView!!.setWebViewClient(SubWebClient());

        /**
         * 获取到设置器
         */
        val settings:WebSettings = mWebView!!.settings;

        /**
         * 使用设置器进行设置
         */
        settings.javaScriptEnabled=true;
        /**
         * 设置支持缩放
         */
        settings.setSupportZoom(true)

        /**
         * 设置加载的网页
         */
        mWebView!!.loadUrl("http://map.baidu.com/");


    }

    /**
     * 地图客户端
     */
    inner class SubWebClient : WebViewClient(){

        /**
         * 设置使用当前客户端
         */
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return true;
        }
    }


}

