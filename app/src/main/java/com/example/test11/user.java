package com.example.test11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class user extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        //나의 메모 리사이클러뷰
        RecyclerView recyclerview_memo = (RecyclerView) v.findViewById(R.id.listview_memo);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview_memo.setLayoutManager(linearLayoutManager);

        //저장한 일정 리사이클러뷰
        RecyclerView recyclerview_usersub = (RecyclerView) v.findViewById(R.id.listview_usersub);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview_usersub.setLayoutManager(linearLayoutManager);



        ImageButton memo = v.findViewById(R.id.memo);
        ImageButton usersub = v.findViewById(R.id.usersub);

        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview_memo.setVisibility(View.VISIBLE);
                recyclerview_usersub.setVisibility(View.GONE);
            }
        });

        usersub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview_memo.setVisibility(View.GONE);
                recyclerview_usersub.setVisibility(View.VISIBLE);

            }
        });
        ImageButton setting = v.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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