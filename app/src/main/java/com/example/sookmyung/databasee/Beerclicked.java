package com.example.sookmyung.databasee;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;

public class Beerclicked extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerclicked);
        Intent intent = getIntent(); /*데이터 수신*/

        String findstring = intent.getExtras().getString("name"); /*String형*/
        TextView textview = (TextView)findViewById(R.id.textview);
        String Result = null;

        DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();



        Cursor iCursor = mDbOpenHelper.selectColumns();
       String result[]=iCursor.getColumnNames();
        String str = Arrays.toString(result);




        textview.setText(str);
        while (iCursor.moveToNext()) {
            String tempID = iCursor.getString(iCursor.getColumnIndex("id"));
            String tempName = iCursor.getString(iCursor.getColumnIndex("name"));
            String tempDosu = iCursor.getString(iCursor.getColumnIndex("dosu"));
            String tempCountry = iCursor.getString(iCursor.getColumnIndex("country"));
            String tempCategary = iCursor.getString(iCursor.getColumnIndex("category"));
            if (tempName.equals(findstring)) {
                Result = tempID + "," + tempName + "," + tempDosu + "," + tempCategary;
                textview.setText("Result");  }

        }
       ;


    }
}