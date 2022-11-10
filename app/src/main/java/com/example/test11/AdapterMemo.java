package com.example.test11;
// https://3001ssw.tistory.com/201

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMemo extends RecyclerView.Adapter<ViewHolder3> {//메모정보 어뎁터

    ArrayList<memodata> mo_List;//textview3 날짜

    Activity activity;

    public AdapterMemo(ArrayList<memodata> mo_List) {
        this.mo_List=mo_List;

    }
    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_user,parent, false);
        ViewHolder3 viewholder = new ViewHolder3(context, view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder3 holder, @SuppressLint("RecyclerView") int position) {

        holder.contents.setText(mo_List.get(position).getContents());
        holder.time.setText(mo_List.get(position).getDate());
        /*holder.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                }

                String num=mo_List.get(pos).getNum();
                Log.d("클릭이벤트", num);

                //excute 진행하는 부분
                insertData insert = new insertData();
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/subscribe/push/"+sublist[3]+"?"+"date="+sublist[0]+"&id="+sublist[1]+"&title="+sublist[2], "4");


            }
        });*/
    }
    @Override
    public int getItemCount() {
        return mo_List.size();
    }
    public void setCm_List(ArrayList<memodata> mo_list) {
        notifyDataSetChanged();

    }
    public ArrayList<memodata> getMg_List(){
        return mo_List;
    }
}