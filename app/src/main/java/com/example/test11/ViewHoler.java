package com.example.test11;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView com_name;
    public Button sub;
    public TextView time;
    public Button url;

    ViewHolder(Context context, View itemView){
        super(itemView);
        title = itemView.findViewById(R.id.calander_title);
        sub = itemView.findViewById(R.id.sub1);
        time = itemView.findViewById(R.id.calander_time);
        com_name = itemView.findViewById(R.id.Calader_com);
        url = itemView.findViewById(R.id.url);


    }
}
