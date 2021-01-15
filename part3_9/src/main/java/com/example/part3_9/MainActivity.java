package com.example.part3_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText contentView;
    Button btn;

    //퍼미션 부여 관련
    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = findViewById(R.id.content);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        //checkSelfPermission을 했을때, 되어 있으면 PackageManager.PERMISSION.GRANTED return.
        if((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE))== PackageManager.PERMISSION_GRANTED){
            fileReadPermission = true;
        }
        if((ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE))==PackageManager.PERMISSION_GRANTED){
            fileWritePermission = true;
        }

        //퍼미션이 아직 안되어 있는게 있다면
        if(!fileReadPermission || !fileWritePermission){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

        }

    }

    //ActivityCompat.requestPermission 다음에 자동으로 실행
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) fileReadPermission = true;
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED) fileWritePermission = true;
        }
    }

    @Override
    public void onClick(View view) {
        String content = contentView.getText().toString();
        if(fileWritePermission && fileReadPermission){
            FileWriter writer;
            try{
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                File dir = new File(dirPath);
                if(!dir.exists()){
                    dir.mkdir();
                }
                File file = new File(dir + "/myfile.txt");
                if(!file.exists()){
                    file.createNewFile();
                }
                writer = new FileWriter(file,true);
                writer.write(content);
                writer.flush();
                writer.close();

                Intent intent = new Intent(this,ReadFileActivity.class);
                startActivity(intent);
            }catch (Exception e){e.printStackTrace();}
        }
        else{
            Toast.makeText(this, "permission이 부여 되지 않아 기능을 수행할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
