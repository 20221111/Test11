package com.example.test11;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ViewHolder3 extends RecyclerView.ViewHolder {
    public TextView contents;
    public TextView time;

    public ViewHolder3(Context context, View itemView){
        super(itemView);

        contents = itemView.findViewById(R.id.textView1);
        time = itemView.findViewById(R.id.textView3);
    }
}
