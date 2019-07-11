package com.example.gonghack;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView mwv;
    Button one,two,three,four,five;
    TextView Viewing;
    public String selected="1";
    public String murl="";
    public String mname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Viewing=(TextView)findViewById(R.id.viewing_scene);
        one=(Button)findViewById(R.id.one);
        two=(Button)findViewById(R.id.two);
        three=(Button)findViewById(R.id.three);
        four=(Button)findViewById(R.id.four);
        five=(Button)findViewById(R.id.five);

        //------------------------------------------------------------------------------
        //
        //
        //1번 데이터베이스 가져오기
        //
        //1번 카메라 DB가 존재하지 않는다면 가지고온다
        //

        ifnotexist("1");
        mname=getName("1");
        murl=getAddress("1");
        Viewing.setText(mname);
        //
        //
        //
        //
        //

        //
        //
        //WebView
        //
        //
        setWebView(murl);
        //
        //
        //

    }

    public void ifnotexist(String id){
        boolean exists_one=searchtable(id);
        if(exists_one==false){
            DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
            mDbOpenHelper.open();
            mDbOpenHelper.create();

            mDbOpenHelper.insertColumn(id,"카메라를 입력 해주세요"," ");
            mDbOpenHelper.close();
        }
    }


    public boolean searchtable(String IDs) {
        DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        //읽어오기 IDs번 카메라
        Cursor iCursor = mDbOpenHelper.selectColumns();
        while (iCursor.moveToNext()) {
            String tempID = iCursor.getString(iCursor.getColumnIndex("camid"));
            String tempName = iCursor.getString(iCursor.getColumnIndex("camname"));
            String tempAddress = iCursor.getString(iCursor.getColumnIndex("camaddress"));
            if (tempID.equals(IDs)) {
                mDbOpenHelper.close();
                return true;
            }
        }
        mDbOpenHelper.close();
        return false;
    }

    public String getName(String id){
        DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        //읽어오기 IDs번 카메라
        Cursor iCursor = mDbOpenHelper.selectColumns();
        while (iCursor.moveToNext()) {
            String tempID = iCursor.getString(iCursor.getColumnIndex("camid"));
            String tempName = iCursor.getString(iCursor.getColumnIndex("camname"));
            String tempAddress = iCursor.getString(iCursor.getColumnIndex("camaddress"));
            if (tempID.equals(id)) {
                mDbOpenHelper.close();
                return tempName;
            }
        }
        mDbOpenHelper.close();
        return "DB가 없습니다.";
    }

    public String  getAddress(String id){
        DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        //읽어오기 IDs번 카메라
        Cursor iCursor = mDbOpenHelper.selectColumns();
        while (iCursor.moveToNext()) {
            String tempID = iCursor.getString(iCursor.getColumnIndex("camid"));
            String tempName = iCursor.getString(iCursor.getColumnIndex("camname"));
            String tempAddress = iCursor.getString(iCursor.getColumnIndex("camaddress"));
            if (tempID.equals(id)) {
                mDbOpenHelper.close();
                return tempAddress;
            }
        }
        mDbOpenHelper.close();
        return "DB가 없습니다.";
    }

    public void setWebView(String urls){
        mwv=(WebView)findViewById(R.id.WebView);

        WebSettings mws=mwv.getSettings();//Mobile Web Setting
        mws.setJavaScriptEnabled(true);//자바스크립트 허용
        mws.setLoadWithOverviewMode(true);//컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
        mws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mwv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mwv.loadUrl(urls);
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


    //popup 화면 띄우기
    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("name",Viewing.getText());
        intent.putExtra("selected",selected);
        intent.putExtra("address",murl);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String result = data.getStringExtra("result");
                String addresses= data.getStringExtra("resultaddress");
                String selectedd = data.getStringExtra("selected");
                murl=addresses;
                selected=selectedd;
                Viewing.setText(result);

            }
        }
    }


    public void cone(View v){

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c11));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="1";
        ifnotexist("1");
        mname=getName("1");
        murl=getAddress("1");
        Viewing.setText(mname);
        setWebView(murl);
    }
    public void ctwo(View v){

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c21));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="2";
        ifnotexist("2");
        mname=getName("2");
        murl=getAddress("2");
        Viewing.setText(mname);
        setWebView(murl);
    }
    public void cthree(View v){

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c31));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="3";
        ifnotexist("3");
        mname=getName("3");
        murl=getAddress("3");
        Viewing.setText(mname);
        setWebView(murl);
    }
    public void cfour(View v){

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c41));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="4";
        ifnotexist("4");
        mname=getName("4");
        murl=getAddress("4");
        Viewing.setText(mname);
        setWebView(murl);
    }
    public void cfive(View v){


        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c51));
        selected="5";
        ifnotexist("5");
        mname=getName("5");
        murl=getAddress("5");
        Viewing.setText(mname);
        setWebView(murl);
    }



}
