package com.example.test11;

import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;

public class user_login_info extends AppCompatActivity {//전체정보 수정 (한개만 바꿔도 상관없도록 만들어야함)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_info);

        ImageButton enter = findViewById(R.id.enter);
        EditText editname=findViewById(R.id.editText6);
        EditText editemail=findViewById(R.id.edit_email);
        EditText editque=findViewById(R.id.editquestion);
        editname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editname.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        editemail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editemail.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        editque.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editque.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String newname= String.valueOf(editname.getText());
                String newemail= String.valueOf(editemail.getText());
                String newques= String.valueOf(editque.getText());
                //Log.d("비밀번호 입력확인", currentpas);
                MainActivity ma=new MainActivity();
                insertData insert = new insertData(); //로그인 가동
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Acoount/changeAccount/"+ma.memberid+"?email="+newemail+"&name="+newname+"&security="+newques, "4");

                new Handler().postDelayed(new Runnable() {//성공적으로 변경되면 다음화면으로 넘어감
                    @Override
                    public void run() {
                        Log.d("비밀번호 입력확인",insert.loginresult);
                        switch (insert.loginresult){
                                case "true":
                                    Intent intent = new Intent(getApplicationContext(), CLD.class);
                                    startActivity(intent);
                                    break;
                                case "false":
                                    //이부분 토스트추가
                                    break;

                            }

                        }
                    }, 1000);


            }
        });
    }
}