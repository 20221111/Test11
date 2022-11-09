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

public class Forgot_ID extends AppCompatActivity {
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotid);


        EditText name =findViewById(R.id.name);
        EditText email =findViewById(R.id.email);

        name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(name.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });


        email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {//엔터 누르면 키보드 내려감 이뮬레이터에서는 안됨 노트북 엔터

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(email.getWindowToken(), 0);    //hide keyboard
                    return true;
                }
                return false;
            }
        });


        ImageButton find_password = findViewById(R.id.forgot_id);
        TextView Show_ID =findViewById(R.id.ShowID);
        find_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                String f_name= String.valueOf(name.getText());
                String f_email= String.valueOf(email.getText());

                //http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/findpassword/nmy6452?security=test

                insertData insert = new insertData(); //로그인 가동
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/findid/"
                        + f_name + "?email=" + f_email,"1");
                //성공실패
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("아이디 찾기", String.valueOf(insert.findid));

                        if(String.valueOf(insert.findid) != null)
                        {
                            Log.d("아이디 찾기", "아이디 찾기 성공");
                            Toast.makeText(Forgot_ID.this, "아이디를 찾았습니다.", Toast.LENGTH_SHORT).show();
                            Show_ID.setText(insert.findid);
                        }
                        else
                        {
                            Log.d("아이디 찾기", "아이디 찾기 실패");
                            Toast.makeText(Forgot_ID.this, "입력이 잘못됬습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);

            }
        });
    }

}
