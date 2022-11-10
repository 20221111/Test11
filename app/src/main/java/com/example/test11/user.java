package com.example.test11;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class user extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        MainActivity ma=new MainActivity();
        TextView name = (TextView) v.findViewById(R.id.editText8);
        name.setText(ma.memberid);

        //나의 메모 리사이클러뷰
        LinearLayoutManager linearLayoutManager;

        RecyclerView recyclerview_memo = (RecyclerView) v.findViewById(R.id.listview_memo);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview_memo.setLayoutManager(linearLayoutManager);

        //저장한 일정 리사이클러뷰
        RecyclerView recyclerview_usersub = (RecyclerView) v.findViewById(R.id.listview_usersub);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview_usersub.setLayoutManager(linearLayoutManager);



        //구독한일정 띄우는 api
        insertData read = new insertData();
        read.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/subscribe/Show/"+ma.memberid, "5");
        recyclerview_usersub.setAdapter(read.au);//selectData에서 add해도 성공
        read.au.notifyDataSetChanged();

        //메모 띄우는 api
        insertData read1 = new insertData();
        read1.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Memo/Show/"+ma.memberid, "7");
        recyclerview_memo.setAdapter(read1.am);//selectData에서 add해도 성공
        read1.am.notifyDataSetChanged();

        //1. insertdata에서 메모일정 파싱(완료
        //2. Arraylist에 담기(완료
        //3. 어뎁터 구성(완료
        //4. 어뎁터 연결

        ImageView rec_memo = (ImageView) v.findViewById(R.id.rec_memo);
        ImageView rec_usersub = (ImageView) v.findViewById(R.id.rec_usersub);
        ImageView rec_setting = (ImageView) v.findViewById(R.id.rec_setting);
        ImageButton memo = v.findViewById(R.id.memo);
        ImageButton usersub = v.findViewById(R.id.usersub);

        //초기 화면은 아무것도 보이지 않도록
        recyclerview_memo.setVisibility(View.GONE);
        recyclerview_usersub.setVisibility(View.GONE);
        rec_memo.setVisibility(View.GONE);
        rec_usersub.setVisibility(View.GONE);
        rec_setting.setVisibility(View.GONE);


        //나의 메모 클릭 시
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview_memo.setVisibility(View.VISIBLE);
                recyclerview_usersub.setVisibility(View.GONE);
                rec_memo.setVisibility(View.VISIBLE);
                rec_usersub.setVisibility(View.GONE);
                rec_setting.setVisibility(View.GONE);

            }
        });

        //저장한 일정 클릭 시
        usersub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview_memo.setVisibility(View.GONE);
                recyclerview_usersub.setVisibility(View.VISIBLE);
                rec_memo.setVisibility(View.GONE);
                rec_usersub.setVisibility(View.VISIBLE);
                rec_setting.setVisibility(View.GONE);

            }
        });

        ImageButton setting = v.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), user_login.class);
                startActivity(intent);
                rec_memo.setVisibility(View.GONE);
                rec_usersub.setVisibility(View.GONE);
                rec_setting.setVisibility(View.VISIBLE);
            }
        });
        return v;
    }


//    public void addLayout() {
//        LayoutInflater inflater = (LayoutInflater)getSystemService(
//                getContext(LAYOUT_INFLATER_SERVICE)
//        )
//    }



}