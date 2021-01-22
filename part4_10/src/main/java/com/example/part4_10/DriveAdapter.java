package com.example.part4_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    //1개의 작은 요소를 만들어주는것 ( 10개의 작은 뷰가 생기면, 10번 호출) - 성능 주의!
    //처음 만들어질때만 findViewById를 하고, inflater 초기화를 한다.
    //왜냐하면, 한번 만들어지면 convertView를 리턴하기 때문에 이걸로 판단하면 된다.
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //해당 뷰가 처음 만들어지는 것! : 초기화 & 새로 findViewById하기
        if(convertView == null){
            //초기화
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId,null);
            //holder이라는 변수에 findViewById 해서 값 집어넣어서 convertView에 저장
            DriveHolder holder = new DriveHolder(convertView);
            //convertView에 holder이라는 non-visible data를 보관한다. ( findViewById가 들어잇음)
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
                Toast toast = Toast.makeText(context,vo.title+" menu click",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return convertView;
    }
}
