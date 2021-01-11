package com.example.part2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button vibButton;
    Button systemButton;
    Button customButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibButton = findViewById(R.id.vibration);
        systemButton = findViewById(R.id.systemBeep);
        customButton = findViewById(R.id.customSound);

        vibButton.setOnClickListener(this);
        systemButton.setOnClickListener(this);
        customButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == vibButton){
            Vibrator vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        }else if (view == systemButton){
            //벨소리 종류 : 알람 벨소리 (기본 시스템)
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //ringtone에 원하는 노래 설정 : 여기선 notification의 노래 설정
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),notification);
            ringtone.play();
        } else if(view == customButton){
            MediaPlayer player = MediaPlayer.create(this,R.raw.fallbackring);
            player.start();
        }
    }
}
