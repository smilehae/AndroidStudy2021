package com.example.part4_10;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] arrayDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView arrayView = findViewById(R.id.main_listview_array);
        arrayView.setOnItemClickListener(this);
        ListView simpleView = findViewById(R.id.main_listview_simple);
        ListView cursorView = findViewById(R.id.main_listview_cursor);

        //arrayAdapter은 array를 이용해서 아이템 1개만 보여줄때 사용한다.
        arrayDatas=getResources().getStringArray(R.array.location);
        //이 컨텍스트를 받고, 한 항목당 1개의 내용만 표시되는 리스트뷰를 만들겠습니다! 그리고 내용은 arrayDatas에서 가져올게요! 라는 뜻
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayDatas);
        //만든 어댑터를 listview랑 연결해주면 된다.
        arrayView.setAdapter(arrayAdapter);

        //simpleDatas 는 Hashmap을 요소로 가지는 ArrayList
        ArrayList<HashMap<String,String>> simpleDatas = new ArrayList<>();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_data",null);
        while (cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<>();
            //put 할때 key , value 식
            //getString이 1부터 시작되는건, 0번째 위치에는 _id가 있기 때문 ( 정렬용)
            //map이라는 HashMap<String, String> 에 name, content2개의 내용이 들어가게 된다.
            map.put("name",cursor.getString(1));
            map.put("content",cursor.getString(2));
            simpleDatas.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,simpleDatas,android.R.layout.simple_list_item_2,
                new String[]{"name","content"},new int[]{android.R.id.text1, android.R.id.text2});
        simpleView.setAdapter(adapter);

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{"name","content"},
                new int[]{android.R.id.text1,android.R.id.text2},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        cursorView.setAdapter(cursorAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast t = Toast.makeText(this,arrayDatas[position],Toast.LENGTH_SHORT);
        t.show();
    }
}
