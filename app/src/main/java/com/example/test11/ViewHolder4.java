package com.example.test11;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ViewHolder4 extends RecyclerView.ViewHolder {//검색 뷰홀더
    public TextView title;
    public TextView com_name;
    public TextView time;
    public TextView date;
    public Button url;

    ViewHolder4(Context context, View itemView){
        super(itemView);
        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);
        time = itemView.findViewById(R.id.time);
        com_name = itemView.findViewById(R.id.name);
        url = itemView.findViewById(R.id.url);


    }
}
