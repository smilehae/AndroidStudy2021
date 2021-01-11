package com.example.part2_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Lab5_2Activity extends AppCompatActivity implements View.OnClickListener{

    Button alertBtn;
    Button listBtn;
    Button dateBtn;
    Button timeBtn;
    Button customDialogBtn;

    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5_2);

        alertBtn = findViewById(R.id.btn_alert);
        listBtn = findViewById(R.id.btn_list);
        dateBtn = findViewById(R.id.btn_date);
        timeBtn= findViewById(R.id.btn_time);
        customDialogBtn = findViewById(R.id.btn_custom);

        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customDialogBtn.setOnClickListener(this);

    }

    private void showToast(String message){
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_SHORT);
        toast.show();
    }

    DialogInterface.OnClickListener dialogListner = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(dialogInterface == customDialog && i == DialogInterface.BUTTON_POSITIVE){
                showToast("custom dialog 확인 click");
            } else if(dialogInterface == listDialog){
                String[] datas = getResources().getStringArray(R.array.dialog_array);
                showToast(datas[i]+"선택 하셨습니다.");
            } else if(dialogInterface == alertDialog && i == DialogInterface.BUTTON_POSITIVE){
                showToast("alert dialog OK Clicked...");
            }
        }
    };

    @Override
    public void onClick(View view) {
        if(view == alertBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("알림");
            builder.setMessage("정말 종료하겠습니까?");
            builder.setPositiveButton("OK",dialogListner);
            builder.setNegativeButton("No",null);

            alertDialog = builder.create();
            alertDialog.show();
        } else if (view == listBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알람 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array,0,dialogListner);
            builder.setPositiveButton("확인",null);
            builder.setNegativeButton("취소",null);
            listDialog = builder.create();
            listDialog.show();
        }
    }
}