package com.example.gonghack;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PopupActivity extends AppCompatActivity {

    EditText title,address;
    String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        title=(EditText)findViewById(R.id.txtText);
        address=(EditText)findViewById(R.id.addressText);

        Intent intent = getIntent();
        String data = intent.getStringExtra("name");
        selected= intent.getStringExtra("selected");
        String maddress = intent.getStringExtra("address");
        title.setText(data);
        address.setText(maddress);

    }


    public void mOnClose(View v){
        String titles=title.getText().toString();
        String addresses=address.getText().toString();
        String aselected = selected;


        //data에 저장
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_SELECTED,aselected);
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_TITLE,titles);
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS,addresses);

        SQLiteDatabase db = DBHelper.getInstance(this).getWritableDatabase();

        long newRowId = db.insert(DBContract.DBEntry.TABLE_NAME,null,contentValues);


        if(newRowId==-1){
            Toast.makeText(this,"저장에 문제 발생",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,selected+"번 카메라값이 변경되었습니다.",Toast.LENGTH_SHORT).show();
        }



        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", titles);
        intent.putExtra("resultaddress",addresses);
        intent.putExtra("selected",selected);
        Log.d("팝업에서 보냄",title.getText().toString());
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
