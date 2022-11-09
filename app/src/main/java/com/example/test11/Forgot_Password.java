package com.example.test11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Forgot_Password extends AppCompatActivity {
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);


        EditText Check_id =findViewById(R.id.editText4);
        EditText Check_sec =findViewById(R.id.editText);

        Check_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Check_id.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });


        Check_sec.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Check_sec.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });


        ImageButton find_password = findViewById(R.id.imageButton4);
        TextView Show_password =findViewById(R.id.text_Show_password);
        find_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                String f_id= String.valueOf(Check_id.getText());
                String f_security= String.valueOf(Check_sec.getText());

                //http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/findpassword/nmy6452?security=test

                insertData insert = new insertData(); //로그인 가동
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/findpassword/"
                        + f_id + "?security=" + f_security,"2");
                //성공실패
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("비밀번호 찾기", String.valueOf(insert.findpw));

                        if(String.valueOf(insert.findpw) != null)
                        {
                            Log.d("비밀번호 찾기", "비밀번호 찾기 성공");
                            Toast.makeText(Forgot_Password.this, "변경된 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                            Show_password.setText(insert.findpw);
                        }
                        else
                        {
                            Log.d("비밀번호 찾기", "비밀번호 찾기 실패");
                            Toast.makeText(Forgot_Password.this, "입력이 잘못됬습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);

            }
        });

        ImageButton backbut = findViewById(R.id.imageButton5);
        backbut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
