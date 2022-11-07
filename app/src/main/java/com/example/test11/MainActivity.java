package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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



        //insertData insert = new insertData(); 회원가입 테스트용이었음.
        //insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/SignUp/"+jm.getId()+"?", "0");
        //commMain cm =new commMain();


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

        //Sign in 클릭 시 메인(캘린더) 화면으로 이동
        ImageButton Sign_in = findViewById(R.id.Sign_in);
        Sign_in.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CLD.class);
                startActivity(intent);
            }
        });
    }

    /*private void setAlarm() {
        AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, receiverIntent, 0);

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

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);


    }*/
}