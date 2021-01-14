package com.example.part3_8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    //SQLiteOpenHelper은 추상 클래스이므로 Override가 필수이다.

    public DBHelper(Context context){
        super(context,"memodb",null,DATABASE_VERSION);
    }

    @Override
    //가장 처음에 호출
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String memoSQL = "create table tb_memo" +
                "(_id integer primary key autoincrement," +
                "title, content)";
        sqLiteDatabase.execSQL(memoSQL);
        //create table tb_memo (_id integer primary key autoincrement , title, content)
        //tb_memo 라는 테이블을 만들었고, 칼럼은 _id, title, content 3개이다.

    }

    //DATABASE_VERSION의 값이 변경되면 onUpgrade가 자동 호출. 잘못 만든 테이블을 없애고 다시 만든다.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        if(newversion == DATABASE_VERSION){
            //버전이 바뀌면 tb_memo를 없애고 새로 만들기
            sqLiteDatabase.execSQL("drop table tb_memo");
            onCreate(sqLiteDatabase);
        }
    }
}
