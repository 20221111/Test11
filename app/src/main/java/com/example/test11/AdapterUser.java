package  com.example.test11;
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

public class AdapterUser extends RecyclerView.Adapter<ViewHolder2> {//일정구독 어댑터

    ArrayList<subscribe> ss_List;//textview3 날짜

    Activity activity;

    public AdapterUser( ArrayList<subscribe> ss_List) {
        this.ss_List=ss_List;

    }
    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_usersub,parent, false);
        ViewHolder2 viewholder = new ViewHolder2(context, view);
        return viewholder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(ss_List.get(position).getTitle());
        holder.time.setText(ss_List.get(position).getDate());
        /*holder.sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               *//* int pos= holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                }
                sublist[1]=jm.getId();
                sublist[0]=tt_List.get(pos).getMeeting_DATE();
                sublist[2]=tt_List.get(pos).getTitle();
                sublist[3]=tt_List.get(pos).getType();
                Log.d("클릭이벤트", tt_List.get(pos).getTitle());

                insertData insert = new insertData();
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/subscribe/push/"+sublist[3]+"?"+"date="+sublist[0]+"&id="+sublist[1]+"&title="+sublist[2], "4");
                commMain cm =new commMain();

*//*
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return ss_List.size();
    }
    public void setCm_List(ArrayList<subscribe> ss_list) {
        notifyDataSetChanged();

    }
    public ArrayList<subscribe> getMg_List(){
        return ss_List;
    }
}