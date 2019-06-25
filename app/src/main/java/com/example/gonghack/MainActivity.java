package com.example.gonghack;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    WebView mwv;
    Button one,two,three,four,five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one=(Button)findViewById(R.id.one);
        two=(Button)findViewById(R.id.two);
        three=(Button)findViewById(R.id.three);
        four=(Button)findViewById(R.id.four);
        five=(Button)findViewById(R.id.five);


        //
        //
        //WebView
        //
        //
        mwv=(WebView)findViewById(R.id.WebView);

        WebSettings mws=mwv.getSettings();//Mobile Web Setting
        mws.setJavaScriptEnabled(true);//자바스크립트 허용
        mws.setLoadWithOverviewMode(true);//컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정

        mwv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mwv.loadUrl("http://www.naver.com");
        //
        //
        //

    }

    //   추가전에 뒤로가기 이벤트 호출시 홈으로 돌아갔으나, 이젠 일반적인 뒤로가기 기능 활성화
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mwv.canGoBack()) {
                mwv.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }



}
