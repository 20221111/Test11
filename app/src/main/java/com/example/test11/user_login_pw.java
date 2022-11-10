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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class user_login_pw extends AppCompatActivity {//패스워드만 수정
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_pw);

        ImageButton enter = findViewById(R.id.enter);
        EditText curr_pass=findViewById(R.id.c_password);
        EditText new_pass=findViewById(R.id.editText3);
        EditText new_passcheck=findViewById(R.id.newpassword);
        curr_pass.setOnKeyListener(new View.OnKeyListener() { //현재 비밀번호
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(curr_pass.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        new_pass.setOnKeyListener(new View.OnKeyListener() { //새로운 비밀번호
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(new_pass.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        new_passcheck.setOnKeyListener(new View.OnKeyListener() { //새로운 비밀번호 확인
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(new_passcheck.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String currentpas= String.valueOf(curr_pass.getText());
                String newpass= String.valueOf(new_pass.getText());
                String newpass_check= String.valueOf(new_passcheck.getText());
                //Log.d("비밀번호 입력확인", currentpas);
                MainActivity ma=new MainActivity();
                insertData insert = new insertData(); //로그인 가동

                if(newpass.equals(newpass_check)){//새 비밀번호와 확인비번 일치할경우
                    Log.d("비밀번호 입력확인", "성공");
                    insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Acoount/changepassword/"+ma.memberid+"?curpassword="+currentpas+"&password="+newpass, "4");

                    new Handler().postDelayed(new Runnable() {//성공적으로 변경되면 다음화면으로 넘어감
                        @Override
                        public void run() {
                            Log.d("비밀번호 입력확인",insert.loginresult);
                            switch (insert.loginresult){
                                case "true":
                                    Intent intent = new Intent(getApplicationContext(), CLD.class);
                                    startActivity(intent);
                                    Toast.makeText(user_login_pw.this, "비밀번호가 변경됐습니다.", Toast.LENGTH_SHORT).show();
                                    break;
                                case "false":
                                    //이부분 토스트추가+ 입력된 내용 초기화
                                    Toast.makeText(user_login_pw.this, "비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                                    curr_pass.setText(null);
                                    new_pass.setText(null);
                                    new_passcheck.setText(null);
                                    break;

                            }

                        }
                    }, 1000);

                }

            }
        });
    }
}