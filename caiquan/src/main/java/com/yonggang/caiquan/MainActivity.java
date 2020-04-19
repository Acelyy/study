package com.yonggang.caiquan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String name;

    private TextView userScore;
    private TextView compScore;

    private ListView listRecord;

    private Button shitou;
    private Button jiandao;
    private Button bu;

    private Button restart;

    List<String> infos = new ArrayList<>();

    private ArrayAdapter<String> mAdapter;

    int user_score = 0;
    int comp_score = 0;

    private Map<Integer, String> rules = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取上一个界面传过来的包裹
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            if (name != null) {
                Toast.makeText(this, "欢迎" + name + "用户", Toast.LENGTH_SHORT).show();
            }
        }

        userScore = findViewById(R.id.userScore);
        compScore = findViewById(R.id.compScore);

        listRecord = findViewById(R.id.listRecord);

        shitou = findViewById(R.id.shitou);
        jiandao = findViewById(R.id.jiandao);
        bu = findViewById(R.id.bu);

        restart = findViewById(R.id.restart);

        rules.put(1, "石头");
        rules.put(2, "剪刀");
        rules.put(3, "布");

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infos);
        listRecord.setAdapter(mAdapter);

        shitou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                getResult(1, random.nextInt(3) + 1);
            }
        });

        jiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                getResult(2, random.nextInt(3) + 1);
            }
        });

        bu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Random random = new Random();
                getResult(3, random.nextInt(3) + 1);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comp_score = 0;
                user_score = 0;
                compScore.setText("");
                userScore.setText("");
                infos.clear();
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    // 写一个石头剪刀布的输赢算法
    private void getResult(int player, int computer) {

        Log.i("player", "" + player);
        Log.i("computer", "" + computer);

        if ((player == 1 && computer == 2)
                || (player == 2 && computer == 3)
                || (player == 3 && computer == 1)) {
            // 玩家赢
            user_score++;
            infos.add("玩家出" + rules.get(player) + ",电脑出" + rules.get(computer) + ",玩家赢");
        } else if (player == computer) {
            // 平手
            infos.add("玩家出" + rules.get(player) + ",电脑出" + rules.get(computer) + ",平手");
        } else {
            comp_score++;
            infos.add("玩家出" + rules.get(player) + ",电脑出" + rules.get(computer) + ",电脑赢");
        }

        userScore.setText(name + "：" + user_score + "分");
        compScore.setText("电脑：" + comp_score + "分");

        mAdapter.notifyDataSetChanged();

    }
}
