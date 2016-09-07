package com.example.kwanwoo.listviewtest;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터 원본 준비
        String[] items = {"item1", "item2", "item3", "item4","item5","item6","item7","item8"};

        //어댑터 준비1 (배열 객체 이용, simple_list_item_1 리소스 사용
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        //어댑터 준비2 (리소스 배열 객체 이용, simple_list_item_1 리소스 사용
        // ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_list_item_1);

        //어댑터 준비3 (배열 객체 이용, R.layout.item(사용자 정의) 리소스 사용
        //ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.item, items);

        //어댑터 연결
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapt);

/*        // ListView 기타 설정
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setDivider(new ColorDrawable(Color.RED));
        list.setDividerHeight(5);
*/

        /*     // android.R.layout.simple_list_item_2 테스트
        ArrayList<HashMap<String, String>> mlist = new ArrayList<HashMap<String, String>> ();

        HashMap<String, String> map = new HashMap<String, String> ();
        map.put("item1", "musart");
        map.put("item2", "10");
        mlist.add(map);

        map = new HashMap<String, String> ();
        map.put("item1", "sasim");
        map.put("item2", "20");

        mlist.add(map);

        map = new HashMap<String, String> ();
        map.put("item1", "vincent");
        map.put("item2", "30");

        mlist.add(map);

        SimpleAdapter adapt = new SimpleAdapter(this, mlist, android.R.layout.simple_list_item_2, new String[]{"item1", "item2"}, new int[]{android.R.id.text1, android.R.id.text2});


        //어댑터 연결
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapt);
*/


    }
}
