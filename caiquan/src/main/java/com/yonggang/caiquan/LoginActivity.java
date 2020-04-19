package com.yonggang.caiquan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 使用组件之前必须使用findViewbyId
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        // 对登录按钮设置点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的用户名和密码
                String name = username.getText().toString();
                String pass = password.getText().toString();

                if (name.equals("acelyy") && pass.equals("123456")) {
                    // 登陆成功，跳转界面
                    // 初始化Intent
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);// 第一个参数为当前Activity的对象,第二个是参数为跳转的Activtiy
                    // 初始化包裹，用来包装数据
                    Bundle bundle = new Bundle();
                    // 将数据放进包裹
                    bundle.putString("name",name);
                    // 将包裹放进Intent
                    intent.putExtras(bundle);
                    // 跳转界面
                    startActivity(intent);
                } else {
                    // 登录失败
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
