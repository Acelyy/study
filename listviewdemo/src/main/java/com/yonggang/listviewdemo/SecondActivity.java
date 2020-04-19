package com.yonggang.listviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Spinner sp1;// 视图1
    private Spinner sp2;// 视图2

    private List<String> data1 = new ArrayList<>();// 数据源1
    private List<String> data1_1 = new ArrayList<>();// 数据源1_1
    private List<String> data1_2 = new ArrayList<>();// 数据源1_2

    private ArrayAdapter<String> adapter1;// 适配器1
    private ArrayAdapter<String> adapter2;// 适配器2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);

        // 初始化数据
        data1.add("山东省");
        data1.add("江苏省");

        data1_1.add("济南");
        data1_1.add("青岛");
        data1_1.add("潍坊");
        data1_1.add("淄博");
        data1_1.add("烟台");

        data1_2.add("南京");
        data1_2.add("苏州");
        data1_2.add("无锡");
        data1_2.add("镇江");
        data1_2.add("扬州");

        // 初始化适配器
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, data1);
        // 设置Spinner1的适配器
        sp1.setAdapter(adapter1);

        // 设置某一项选中的监听
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SecondActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    // 给sp2设置山东省的数据源
                    case 0:
                        // 初始化适配器
                        adapter2 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_spinner_dropdown_item, data1_1);
                        // 设置Spinner1的适配器
                        sp2.setAdapter(adapter2);

                        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(SecondActivity.this, data1_1.get(position), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        break;
                    // 给sp2设置江苏省的数据源
                    case 1:
                        // 初始化适配器
                        adapter2 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_spinner_dropdown_item, data1_2);
                        // 设置Spinner1的适配器
                        sp2.setAdapter(adapter2);

                        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(SecondActivity.this, data1_2.get(position), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}
