package com.example.test11;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ViewHolder2 extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView time;
    public ImageButton button;

    public ViewHolder2(Context context, View itemView){
        super(itemView);

        title = itemView.findViewById(R.id.textView11);
        time = itemView.findViewById(R.id.textView10);
        button = itemView.findViewById(R.id.but_Delete_subs);
    }
}
