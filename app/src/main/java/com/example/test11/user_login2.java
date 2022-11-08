package com.example.test11;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class user_login2 extends AppCompatActivity {//비밀번호만 변경할건지 전체 정보 변경할건지
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login2);

        ImageButton pw_change = findViewById(R.id.pw_change);
        pw_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), user_login_pw.class);
                startActivity(intent);
            }
        });
        ImageButton info_change = findViewById(R.id.info_change);
        info_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), user_login_info.class);
                startActivity(intent);
            }
        });

    }
}