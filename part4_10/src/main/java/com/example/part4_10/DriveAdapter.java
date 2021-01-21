package com.example.part4_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class DriveAdapter extends ArrayAdapter<DriveVO> {
    Context context;
    int resId;
    ArrayList<DriveVO> datas;

    public DriveAdapter(Context context, int resId, ArrayList<DriveVO> datas){
        super(context,resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //해당 뷰가 처음 만들어지는 것! : 초기화 & 새로 findViewById하기
        if(convertView == null){
            //초기화
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId,null);
            //holder이라는 변수에 findViewById 해서 값 집어넣어서 convertView에 저장
            DriveHolder holder = new DriveHolder(convertView);
            convertView.setTag(holder);
        }
        DriveHolder holder = (DriveHolder)convertView.getTag();

        ImageView typeImageView = holder.typeImageView;
        TextView titleView = holder.titleView;
        TextView dateView = holder.dateView;
        ImageView menuImageView = holder.menuImageView;

        final  DriveVO vo = datas.get(position);

        titleView.setText(vo.title);
        dateView.setText(vo.date);

        if(vo.type.equals("doc")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_type_doc,null));
        }
        else if(vo.type.equals("file")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_type_file,null));
        }
        else if(vo.type.equals("img")){
            typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_type_image,null));
        }

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
