package com.example.test11;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class CLD extends AppCompatActivity {
    private static final String TAG = "CLD";

    //달력
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);
    //탭바
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private fragmentAdapter adapter;
    ArrayList<commMain> cm_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        long mNow;
        Date mDate;
        SimpleDateFormat mFormat=new SimpleDateFormat("yyyy-MM-dd");
        mNow=System.currentTimeMillis();
        mDate=new Date(mNow);
        String searchText=mFormat.format(mDate);
        joinmember jm=new joinmember();
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        selectdata read = new selectdata();
        read.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/calender/month/"+searchText, "0");

        cm_List = new ArrayList<>();
        RecyclerView recyclerview;
        //dapter adapter = new Adapter(cm_List);
        //ArrayList<commMain>cm_List2=adapter.getCm_List();
        //Adapter adapter1=new Adapter(cm_List2);
        recyclerview = findViewById(R.id.listView_result);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(CLD.this,LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        // recyclerview에 adapter 적용
        recyclerview.setAdapter(read.adapter);
        read.adapter.notifyDataSetChanged();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.d("어뎁터왔나", String.valueOf(read.adapter.getItemCount()));//어뎁터에 세팅은 완료

            }
        }, 1000);


        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new fragmentAdapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new home());
        adapter.addFragment(new menu());
        adapter.addFragment(new search());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("홈");
        tabLayout.getTabAt(1).setText("검색");
        tabLayout.getTabAt(2).setText("마이페이지");}

}
