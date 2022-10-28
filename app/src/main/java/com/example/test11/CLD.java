package com.example.test11;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;

import java.util.Locale;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new fragmentAdapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new home());
        adapter.addFragment(new menu());
        adapter.addFragment(new search());
        adapter.addFragment(new user());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        //탭바 아이콘
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(2).setIcon(R.drawable.menu);
        tabLayout.getTabAt(1).setIcon(R.drawable.search);
        tabLayout.getTabAt(3).setIcon(R.drawable.user);}

}
