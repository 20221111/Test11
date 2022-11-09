package com.example.test11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Create_Account extends AppCompatActivity {
//    String str;
//    str=edit.get


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        EditText edit_id =findViewById(R.id.editText2);
        EditText edit_pw =findViewById(R.id.editText3);
        EditText edit_email =findViewById(R.id.editText5);
        EditText edit_name =findViewById(R.id.editText6);
        EditText edit_sec =findViewById(R.id.editText);

        edit_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_id.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });

        edit_pw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_pw.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });

        edit_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_email.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });

        edit_name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_name.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });

        edit_sec.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_sec.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });

        edit_pw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_pw.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });



        ImageButton sign_up = findViewById(R.id.signup);
        sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                String j_id= String.valueOf(edit_id.getText());
                String j_pw= String.valueOf(edit_pw.getText());
                String j_email= String.valueOf(edit_email.getText());
                String j_name= String.valueOf(edit_name.getText());
                String j_sec= String.valueOf(edit_sec.getText());

                //http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/SignUp/tt?email=tt&name=tt&password=tt&security=tt

                insertData insert = new insertData(); //로그인 가동
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/SignUp/"+j_id+"?email="+j_email+"&name="+j_name+"&password="+j_pw+"&security="+j_sec,"4");
                //성공실패
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("회원가입", String.valueOf(insert.loginresult));

                        switch (insert.loginresult){
                            case "true":

                                Log.d("회원가입", "회원가입 성공");
                                Toast.makeText(Create_Account.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                break;

                            case "false":
                                Log.d("로그인", "로그인실패");
                                Toast.makeText(Create_Account.this, "중복된 아이디.", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                }, 1000);

            }
        });
    }

}
