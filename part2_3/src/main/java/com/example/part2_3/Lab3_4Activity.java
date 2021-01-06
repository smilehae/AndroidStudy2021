package com.example.part2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Lab3_4Activity extends AppCompatActivity {

    TextView textView;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_4);

        textView = findViewById(R.id.custom_font);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"xmas.ttf");
        textView.setTypeface(typeface);

        checkBox = findViewById(R.id.checker);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    checkBox.setText("is Checked");
                }
                else{
                    checkBox.setText("is Unchecked");
                }
            }
        });
    }


}