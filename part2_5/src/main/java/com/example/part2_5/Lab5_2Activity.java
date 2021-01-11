package com.example.part2_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

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

    //다이얼로그 내에서 특정 값이 눌렸을때 나타나는 이벤트에 대한 리스너
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

    //dialog는 복잡하기 때문에 builder 클래스를 사용해서 생성한다.
    //버튼을 누르면 builder로 인해서 제작하는 구조
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
        } else if (view == dateBtn){
            //현재 날짜 띄우기 위한 정보
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        showToast(i+":"+i1+":"+i2);
                }
            },year,month,day);
            datePickerDialog.show(); // 빌더가 코드 내부에 들어있는듯!

        } else if (view == timeBtn){
            Calendar c = Calendar.getInstance();
            int hour= c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    showToast(i+":"+i1);
                }
            },hour,minute,false);
            timePickerDialog.show();
        } else if(view == customDialogBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.dialog_layout,null);
            builder.setView(v);
            builder.setPositiveButton("확인",dialogListner);
            builder.setNegativeButton("취소",null);
            customDialog = builder.create();
            customDialog.show();
        }
    }
}