package com.yonggang.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;// 视图

    private List<Person> data;// 数据源

    MyAdapter adapter; // 适配器

    /**
     * AdapterView 需要使用  必须要三要素：AdapterView（视图）、Adapter（适配器）、List或者数组（数据源）
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化视图
        lv = findViewById(R.id.lv);
        // 初始化数据源
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new Person("李阳洋" + i, "123456" + i, "潍坊" + i));
        }
        /*********************************/
        // 第一种方法：创建BaseAdapter的子类，重写四个方法
//        // 初始化适配器
        adapter = new MyAdapter();
//
//        // 给视图设置适配器
//
        lv.setAdapter(adapter);

        // 设置点击事件监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, data.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        /******************************/
        // 第二种，ArrayAdapter  但只适用于单个文本的视图

//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//
//        lv.setAdapter(myAdapter);
        /*******************************/

    }

    class MyAdapter extends BaseAdapter {

        // 确定ListView一共有多少项
        @Override
        public int getCount() {
            return data.size();
        }

        // 确定第position位置的数据
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        // 确定第position位置的id
        @Override
        public long getItemId(int position) {
            return position;
        }

        // 确定第position位置视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("position", "" + position);
            Log.i("position", convertView == null ? "convertView为空" : "convertView不为空");
            // 查找需要显示的layout布局
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                // 初始化View(学生)，只有刚开始的6个学生才会使用这个方法
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
                // 初始化ViewHolder,用来保存view里面的组件
                holder = new ViewHolder();
                // 初始化具体组件，这种情况下，这些组件都是ViewHolder的一个部分
                holder.img = view.findViewById(R.id.img);
                holder.name = view.findViewById(R.id.name);
                holder.phone = view.findViewById(R.id.phone);
                holder.address = view.findViewById(R.id.address);

                // 将ViewHolder与view绑定，让学生穿上衣服
                view.setTag(holder);
            } else {

                // 把出去的学生对象赋值给即将进入教室的学生对象
                view = convertView;
                // 把衣服也脱给他
                holder = (ViewHolder) view.getTag();
            }
            // 根据layout布局初始化组件
//            ImageView img = view.findViewById(R.id.img);
//            TextView name = view.findViewById(R.id.name);
//            TextView phone = view.findViewById(R.id.phone);
//            TextView address = view.findViewById(R.id.address);
            // 给组件赋值
            Person person = data.get(position);
//            img.setImageResource(R.mipmap.ic_launcher_round);
//            name.setText(person.getName());
//            phone.setText(person.getPhone());
//            address.setText(person.getAddress());

            //  之后所有对衣服的操作，都需要使用ViewHolder操作
            holder.img.setImageResource(R.mipmap.ic_launcher_round);
            holder.name.setText(person.getName());
            holder.phone.setText(person.getPhone());
            holder.address.setText(person.getAddress());
            return view;
        }

        class ViewHolder {
            ImageView img;
            TextView name;
            TextView phone;
            TextView address;
        }

    }


}
