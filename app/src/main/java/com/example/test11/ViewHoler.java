package com.example.test11;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView com_name;
    public Button sub;
    public TextView time;
    public Button url;

    ViewHolder(Context context, View itemView){
        super(itemView);
        title = itemView.findViewById(R.id.textView1);
        sub = itemView.findViewById(R.id.sub1);
        time = itemView.findViewById(R.id.textView3);
        com_name = itemView.findViewById(R.id.com_name);
        url = itemView.findViewById(R.id.url);


    }
}
