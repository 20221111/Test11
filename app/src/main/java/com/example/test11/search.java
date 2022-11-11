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
    private EditText editText;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    String findS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_search,container,false);

        LinearLayoutManager linearLayoutManager;

        editText = (EditText) v.findViewById(R.id.Search);
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
//
//    // 검색을 수행하는 메소드
//    public void search(String charText) {
//        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
//        list.clear();
//        // 문자 입력이 없을때는 모든 데이터를 보여준다.
//        if (charText.length() == 0) {
//            list.addAll(arraylist);
//        }
//        // 문자 입력을 할때..
//        else
//        {
//            // 리스트의 모든 데이터를 검색한다.
//            for(int i = 0;i < arraylist.size(); i++)
//            {
//                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
//                if (arraylist.get(i).toLowerCase().contains(charText))
//                {
//                    // 검색된 데이터를 리스트에 추가한다.
//                    list.add(arraylist.get(i));
//                }
//            }
//        }
//        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
//        adapter.notifyDataSetChanged();
//    }
//
//    // 검색에 사용될 데이터를 리스트에 추가한다.
//    private void settingList(){
//        list.add("본회의");
//        list.add("공청회");
//        list.add("전체회의");
//        list.add("소회의");
//    }
//

}