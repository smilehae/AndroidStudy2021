package com.example.part2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lab3_2Activity extends AppCompatActivity implements View.OnClickListener {

    Button trueBtn;
    Button falseBtn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_2);

        trueBtn = findViewById(R.id.visible);
        falseBtn = findViewById(R.id.invisible);
        text = findViewById(R.id.textView);

        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == trueBtn){
            text.setVisibility(View.VISIBLE);
        }
        if(view == falseBtn){
            text.setVisibility(View.INVISIBLE);
        }
    }
}