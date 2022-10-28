package com.example.test11;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ArrayList<commMain> cm_List;
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cm_List = new ArrayList<>();
        
        //String id = null;
        setContentView(R.layout.activity_main);

        joinmember jm=new joinmember();
        insertData insert = new insertData(); //로그인 가동
        insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/"+jm.getId()+"?", "3");
        String id=jm.getId();
        Intent intentMainActivity =
                new Intent(Login.this, CLD.class);
        intentMainActivity.putExtra("nickname", id);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("로그인", String.valueOf(insert.loginresult));
                if(insert.loginresult==true){//로그인성공
                    Log.d("로그인", "로그인성공");
                    Toast.makeText(Login.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    Log.d("로그인", jm.getId());
                    startActivity(intentMainActivity);//로그인에 성공해야 달력화면으로 넘어감
                    //이부분에 코드 추가하면 됨.
                }
                else if(insert.loginresult==false){
                    Log.d("로그인", "로그인실패");
                    Toast.makeText(Login.this, "닉네임과 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }

            }
        }, 1000);


        /*RecyclerView recyclerview;
        Adapter adapter = new Adapter(cm_List);
        //Log.d("어뎁터테스트", String.valueOf(adapter.getItemCount()));//어뎁터에 세팅은 완료
        recyclerview = findViewById(R.id.listView_result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        // recyclerview에 adapter 적용
        recyclerview.setAdapter(adapter);*/
    }

}