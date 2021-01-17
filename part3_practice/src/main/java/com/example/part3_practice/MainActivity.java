package com.example.part3_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editName;
    EditText editPhone;
    EditText editEmail;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName= findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        editEmail = findViewById(R.id.edit_email);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into tb_memo (name, phone,email) values(?,?,?)",new String[]{name,phone,email});
        db.close();

        Intent intent = new Intent(this,ReadActivity.class);
        startActivity(intent);
    }
}
