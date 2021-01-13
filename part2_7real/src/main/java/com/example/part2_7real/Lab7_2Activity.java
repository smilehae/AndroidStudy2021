package com.example.part2_7real;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import java.util.Locale;

public class Lab7_2Activity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //언어를 바꾸는 코드 (locale change) language가 en : 영어 ko : 한국어
        Configuration config = this.getResources().getConfiguration();
        Locale locale = new Locale("ko");
        config.locale = locale;
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());

        //여기까지


        setContentView(R.layout.activity_lab7_2);
    }
}