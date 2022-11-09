package com.example.test11;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import androidx.annotation.Nullable;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.util.Date;

public class user_login extends AppCompatActivity {//비밀번호 확인클래스
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        ImageButton enter = findViewById(R.id.enter);
        EditText editText =findViewById(R.id.check_password);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String currentpas= String.valueOf(editText.getText());
                MainActivity ma = new MainActivity();
                Log.d("비밀번호 입력확인",ma.memberid);
                insertData insert = new insertData(); //로그인 가동
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/"+ma.memberid+"?password="+currentpas, "4");

                new Handler().postDelayed(new Runnable() {//비밀번호 일치해야 다음화면 넘어감
                    @Override
                    public void run() {
                        Log.d("비밀번호 입력확인",insert.loginresult);
                        switch (insert.loginresult){
                            case "true":
                                Intent intent = new Intent(getApplicationContext(), user_login2.class);
                                startActivity(intent);
                                break;
                            case "false":
                                //이부분 토스트추가+ 입력된 내용 초기화
                                break;

                        }

                    }
                }, 1000);

            }
        });
    }
}