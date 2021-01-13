package com.example.part2_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = this.getResources().getConfiguration();
        Locale locale = new Locale("ko");
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());


        setContentView(R.layout.activity_main);



        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btn){
            Toast toast = Toast.makeText(this,"ok button click~~~",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
