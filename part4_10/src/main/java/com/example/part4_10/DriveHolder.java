package com.example.part4_10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//findViewById를 한번만 하고, 그 뒤엔 adapter의 getView 메소드에 넣어서 영구 보관할 목적으로 만든 클래스
public class DriveHolder {
    public ImageView typeImageView;
    public TextView titleView;
    public TextView dateView;
    public ImageView menuImageView;

    public DriveHolder(View root){
        typeImageView = root.findViewById(R.id.custom_item_type_image);
        titleView = root.findViewById(R.id.custom_item_title);
        dateView = root.findViewById(R.id.custom_item_date);
        menuImageView = root.findViewById(R.id.custom_item_menu);
    }

}
