package com.example.test11;
// https://3001ssw.tistory.com/201

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptersearch extends RecyclerView.Adapter<ViewHolder4> {//검색 어댑터

    ArrayList<totalDate> tt_List;//textview3 날짜

    Activity activity;

    public Adaptersearch(ArrayList<totalDate> tt_List) {
        this.tt_List=tt_List;

    }
    @NonNull
    @Override
    public ViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_search,parent, false);
        ViewHolder4 viewholder = new ViewHolder4(context, view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder4 holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(tt_List.get(position).getTitle());
        holder.date.setText(tt_List.get(position).getMeeting_DATE());
        holder.time.setText(tt_List.get(position).getMeeting_TIME());
        holder.com_name.setText(tt_List.get(position).getCommittee_NAME());
        holder.url.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                int pos= holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                }

                intent.setData(Uri.parse(tt_List.get(pos).getUrl()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return tt_List.size();
    }
    public void setCm_List(ArrayList<totalDate> tt_list) {
        notifyDataSetChanged();

    }
    public ArrayList<totalDate> getMg_List(){
        return tt_List;
    }
}