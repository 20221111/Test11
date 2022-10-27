package com.example.test11;

import android.os.Bundle;
import android.util.Log;

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
        setContentView(R.layout.activity_main);

        joinmember jm=new joinmember();
        insertData insert = new insertData(); //로그인 가동
        insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/"+jm.getId()+"?password="+jm.getPassword(), "3");

        RecyclerView recyclerview;
        Adapter adapter = new Adapter(cm_List);
        //Log.d("어뎁터테스트", String.valueOf(adapter.getItemCount()));//어뎁터에 세팅은 완료
        recyclerview = findViewById(R.id.listView_result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        // recyclerview에 adapter 적용
        recyclerview.setAdapter(adapter);
    }

}
