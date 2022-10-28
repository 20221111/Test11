package com.example.test11;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView date;
    public TextView time;
    ViewHolder(Context context, View itemView){
        super(itemView);
        title = itemView.findViewById(R.id.textView1);
        date = itemView.findViewById(R.id.textView2);
        time = itemView.findViewById(R.id.textView2);
    }
}
