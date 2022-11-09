package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Forgot_ID extends AppCompatActivity {

    ArrayList<commMain> cm_List;
    private RecyclerView recyclerview;

    //
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    public joinmember jm=new joinmember();
    static String memberid=null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotid);

        //이름과 이메일이 일치하면 forgot_id 버튼을 눌러서 forgot_ID2 페이지인 아이디를 띄우는 페이지로 전환
        //db랑 일치하는 부분을 어떻게 하는 지 몰라서 로그인하는 부분에서 끌어왔어요, db에 맞게 수정이 좀 필요할 것 같아요
        ImageButton forgot_id = findViewById(R.id.forgot_id);
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
        forgot_id.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                String m_id= String.valueOf(name.getText());
                String m_pw= String.valueOf(email.getText());
                memberid=m_id;

                insertData insert = new insertData(); //로그인 가동
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Login/"+m_id+"?password="+m_pw, "4");


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("로그인", String.valueOf(insert.loginresult));

                        switch (insert.loginresult){
                            case "true":
                                jm.id=m_id;
                                jm.password=m_pw;
                                String id=jm.getId();
                                jm.setId(jm.id);
                                Log.d("아이디", "아이디 찾기");
                                Toast.makeText(Forgot_ID.this, "아이디 찾기 성공!", Toast.LENGTH_SHORT).show();
                                Log.d("아이디", jm.getId());
                                Intent intentMainActivity =
                                        new Intent(Forgot_ID.this, Forgot_ID2.class);
                                intentMainActivity.putExtra("nickname", id);
                                startActivity(intentMainActivity);//로그인에 성공해야 달력화면으로 넘어감
                                break;

                            case "false":
                                Log.d("아이디", "아이디 찾기 실패");
                                Toast.makeText(Forgot_ID.this, "회원정보로 가입된 아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                }, 1000);

            }
        });
    }

}