package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        long mNow;
        Date mDate;
        SimpleDateFormat mFormat=new SimpleDateFormat("yyyy-MM-dd");
        mNow=System.currentTimeMillis();
        mDate=new Date(mNow);
        String searchText=mFormat.format(mDate);

        selectdata read = new selectdata();
        read.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/calender/month/"+searchText, "0");

        //New account 클릭시 sign up 페이지로 이동
        Button NewAccount = findViewById(R.id.NewAccount);
        NewAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Create_Account.class);
                startActivity(intent);
            }
        });

        Button ForgotPassword = findViewById(R.id.ForgotPassword);
        ForgotPassword.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Forgot_Password.class);
                startActivity(intent);
            }
        });
        }
    }
