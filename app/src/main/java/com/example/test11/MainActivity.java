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

public class MainActivity extends AppCompatActivity {

    //private RecyclerView recyclerview;
    //private Adapter adapter = new Adapter();    // adapter 생성
    ArrayList<commMain> cm_List;
    private RecyclerView recyclerview;
    //private Adapter adapter = new Adapter(cm_List);    // adapter 생성

    //달력
    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    public joinmember jm=new joinmember();
    static String memberid=null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //달력
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        mCalender = new GregorianCalendar();
        Log.v("HelloAlarmActivity", mCalender.getTime().toString());



        //New account 클릭시 sign up 페이지로 이동
        Button NewAccount = findViewById(R.id.NewAccount);
        NewAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Create_Account.class);
                startActivity(intent);
                //setAlarm();
            }
        });
        //ForgotPassword 클릭시 forgotpassword 페이지로 이동
        Button ForgotPassword = findViewById(R.id.ForgotPassword);
        ForgotPassword.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Forgot_Password.class);
                startActivity(intent);
            }
        });

        //아이디 찾을 때, ForgotID 클릭 시 Forgot_ID 페이지로
        Button ForgotID = findViewById(R.id.ForgotID);
        ForgotID.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Forgot_ID.class);
                startActivity(intent);
            }
        });

        //로그인 자바는 안쓰는 것 같음
        //Sign in 클릭 시 메인(캘린더) 화면으로 이동
        ImageButton Sign_in = findViewById(R.id.Sign_in);
        EditText edit_id =findViewById(R.id.imageButton2);
        EditText edit_pw =findViewById(R.id.imageButton);

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
        Sign_in.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                String m_id= String.valueOf(edit_id.getText());
                String m_pw= String.valueOf(edit_pw.getText());
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
                                Log.d("로그인", "로그인성공");
                                Toast.makeText(MainActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                Log.d("로그인", jm.getId());
                                Intent intentMainActivity =
                                        new Intent(MainActivity.this, CLD.class);
                                intentMainActivity.putExtra("nickname", id);
                                startActivity(intentMainActivity);//로그인에 성공해야 달력화면으로 넘어감
                                break;

                            case "false":
                                Log.d("로그인", "로그인실패");
                                edit_pw.setText(null);
                                Toast.makeText(MainActivity.this, "닉네임과 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                }, 1000);

            }
        });
    }

    /*private void setAlarm() {
        AlarmReceiver에 값 전달
        //Intent receiverIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, receiverIntent, 0);

        String from = "2020-05-27 10:31:00"; //임의로 날짜와 시간을 지정

        //날짜 포맷을 바꿔주는 소스코드
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;
        try {
            datetime = dateFormat.parse(from);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);

        //alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);


    }*/
}