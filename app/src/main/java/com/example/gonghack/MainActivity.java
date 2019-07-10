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
        //데이터베이스 가져오기
        //
        //
        //

        DBHelper mDbOpenHelper = new DBHelper(this);
        mDbOpenHelper.open();

        Cursor iCursor = mDbOpenHelper.selectColumns();
        while(iCursor.moveToNext()){
            String tempSelected = iCursor.getString(iCursor.getColumnIndex("selected"));
            String temptitle = iCursor.getString(iCursor.getColumnIndex("title"));
            String tempAddress = iCursor.getString(iCursor.getColumnIndex("address"));
            if(tempSelected.equals("1")){
                Viewing.setText(temptitle);
                murl=tempAddress;
            }
        }

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
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title,address FROM Cam WHERE selected='1'",null);

        Viewing.setText(cursor.getString(0));
        murl=cursor.getString(1);
        setWebView(murl);

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c11));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="1";
    }
    public void ctwo(View v){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title,address FROM Cam WHERE selected='2'",null);

        Viewing.setText(cursor.getString(0));
        murl=cursor.getString(1);
        setWebView(murl);

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c21));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="2";
    }
    public void cthree(View v){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title,address FROM Cam WHERE selected='3'",null);

        Viewing.setText(cursor.getString(0));
        murl=cursor.getString(1);
        setWebView(murl);

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c31));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="3";
    }
    public void cfour(View v){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title,address FROM Cam WHERE selected='4'",null);

        Viewing.setText(cursor.getString(0));
        murl=cursor.getString(1);
        setWebView(murl);

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c41));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c5));
        selected="4";
    }
    public void cfive(View v){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT title,address FROM Cam WHERE selected='5'",null);

        Viewing.setText(cursor.getString(0));
        murl=cursor.getString(1);
        setWebView(murl);

        one.setBackground(ContextCompat.getDrawable(this, R.drawable.c1));
        two.setBackground(ContextCompat.getDrawable(this, R.drawable.c2));
        three.setBackground(ContextCompat.getDrawable(this, R.drawable.c3));
        four.setBackground(ContextCompat.getDrawable(this, R.drawable.c4));
        five.setBackground(ContextCompat.getDrawable(this, R.drawable.c51));
        selected="5";
    }



}
