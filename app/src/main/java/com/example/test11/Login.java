package com.example.test11;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ArrayList<commMain> cm_List;
    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cm_List = new ArrayList<>();

        setContentView(R.layout.login);


        EditText edit_id =findViewById(R.id.imageButton2);
        EditText edit_pw =findViewById(R.id.imageButton);

        String m_id= String.valueOf(edit_id.getText());
        String m_pw= String.valueOf(edit_pw.getText());



        joinmember jm=new joinmember();

        insertData insert = new insertData(); //로그인 가동
        insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/"+m_id+"?password="+m_pw, "4");
        String id=jm.getId();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("로그인", String.valueOf(insert.loginresult));

                switch (insert.loginresult){
                    case "true":
                        jm.id=m_id;
                        jm.password=m_pw;
                        Log.d("로그인", "로그인성공");
                        Toast.makeText(Login.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                        Log.d("로그인", jm.getId());
                        Intent intentMainActivity =
                                new Intent(Login.this, home.class);
                        intentMainActivity.putExtra("nickname", id);
                        startActivity(intentMainActivity);//로그인에 성공해야 달력화면으로 넘어감
                        break;

                    case "false":
                        Log.d("로그인", "로그인실패");
                        Toast.makeText(Login.this, "닉네임과 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        }, 1000);

    }
}