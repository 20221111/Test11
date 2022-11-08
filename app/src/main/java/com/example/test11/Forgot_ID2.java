package com.example.test11;


//아이디 찾기 완료 하였을 때 띄우기
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Forgot_ID2 extends AppCompatActivity {
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotid2);

        //찾은 아이디 띄우기

        //enter(확인)누르면 메인엑티비티로 가서 다시 로그인
        ImageButton enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
