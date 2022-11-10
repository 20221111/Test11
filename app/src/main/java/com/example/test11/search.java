package com.example.test11;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class search extends Fragment {
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    String findS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v=inflater.inflate(R.layout.fragment_search,container,false);

        LinearLayoutManager linearLayoutManager;

        editSearch = (EditText) v.findViewById(R.id.editSearch);
        //listView = (ListView) v.findViewById(R.id.listView);

        //검색 리사이클러뷰
        RecyclerView recyclerview_search = (RecyclerView) v.findViewById(R.id.listview_search);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview_search.setLayoutManager(linearLayoutManager);

        editSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_ENTER:
                        findS= String.valueOf(editSearch.getText());
                        insertData read = new insertData();
                        read.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Search/"+findS, "8");
                        recyclerview_search.setAdapter(read.as); //selectData에서 add해도 성공
                        read.as.notifyDataSetChanged();
                }
                return false;
            }
        });



    return v;

    }

}