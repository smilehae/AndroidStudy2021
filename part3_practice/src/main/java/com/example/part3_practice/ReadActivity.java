package com.example.part3_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    TextView nameText;
    TextView phoneText;
    TextView emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        nameText = findViewById(R.id.name_text);
        phoneText = findViewById(R.id.phone_text);
        emailText = findViewById(R.id.email_text);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name,phone,email from tb_memo order by _id  desc limit 1",null);
        while (cursor.moveToNext()){
            nameText.setText(cursor.getString(0));
            phoneText.setText(cursor.getString(1));
            emailText.setText(cursor.getString(2));
        }
        db.close();
    }
}