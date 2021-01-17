package com.example.part3_practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

   public DBHelper(Context context){
       super(context,"memodb",null,DATABASE_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String memoSQL = "create table tb_memo"
                +"(_id integer primary key autoincrement,name,phone,email)";
        sqLiteDatabase.execSQL(memoSQL);
        //tb_memo 라는 테이블에 name, phone, email을 넣었다
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            sqLiteDatabase.execSQL("drop table tb_memo");
            onCreate(sqLiteDatabase);
        }
    }
}
