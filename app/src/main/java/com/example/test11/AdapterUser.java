package  com.example.test11;
// https://3001ssw.tistory.com/201
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
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
        holder.button.setOnClickListener(new View.OnClickListener(){
            @Override//구독 삭제
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                }

                String num=ss_List.get(pos).getNum();
                Log.d("클릭이벤트", num);

                //excute 진행하는 부분
                insertData insert = new insertData();
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/subscribe/pop/"+num, "6");
                //갱신해줘야함
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(insert.memoresult.equals("true")){
                            notifyItemRemoved(pos);
                        }

                    }
                }, 1000);
            }
        });
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