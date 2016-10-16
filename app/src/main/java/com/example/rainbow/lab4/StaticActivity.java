package com.example.rainbow.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2016/10/16.
 */
public class StaticActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_layout);

        final List<Map<String, Object>> data = new ArrayList<>();
        int[] imgName = new int[]{R.mipmap.apple,R.mipmap.banana,R.mipmap.cherry,R.mipmap.coco,
        R.mipmap.kiwi,R.mipmap.orange,R.mipmap.pear,R.mipmap.strawberry,R.mipmap.watermelon};
        String[] fruitName = new String[]{"Apple","Banana","Cherry","Coco","Kiwi","Orange",
        "Pear","Strawberry","Watermelon"};
        for(int i = 0; i < 9; i++){
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("img", imgName[i]);
            temp.put("fruitName", fruitName[i]);
            data.add(temp);
        }

        ListView fruitList = (ListView)findViewById(R.id.fruitList);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item,
                new String[]{"img", "fruitName"}, new int[]{R.id.img, R.id.fruitName});
        fruitList.setAdapter(simpleAdapter);

        fruitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent("Rainbow.example.lab4.staticreceiver");
                Bundle bundle = new Bundle();
                bundle.putString("img", data.get(position).get("img").toString());
                bundle.putString("fruitName", data.get(position).get("fruitName").toString());
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });


    }
}
