package com.example.part3_8;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Lab3_8Activity extends AppCompatActivity implements View.OnClickListener{

    EditText titleView;
    EditText contentView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_8);

        titleView = findViewById(R.id.add_title);
        contentView = findViewById(R.id.add_content);
        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();

        //Realm 객체 가져오기
        Realm.init(this);
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemoVO vo = realm.createObject(MemoVO.class);
                vo.title = title;
                vo.content = content;
            }
        });

        Intent intent = new Intent(this,RealmReadActivity.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}