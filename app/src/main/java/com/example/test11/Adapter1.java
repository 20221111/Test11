package  com.example.test11;
// https://3001ssw.tistory.com/201
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter1 extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<totalDate> tt_List;
    ArrayList<commMain> cm_List;
    //List<?> mergedList;

    Activity activity;
    joinmember jm=new joinmember();
    MainActivity ma=new MainActivity();

    String[] sublist={"date","id","title","type"};

    public Adapter1( ArrayList<totalDate> tt_List) {
        this.tt_List=tt_List;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view,parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);
        return viewholder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //commMain cm = cm_List.get(position);
        //String mg= (String) mergedList.get(position);
        //String imageurl=drugimage.getDrugImg();
        holder.title.setText(tt_List.get(position).getTitle());
        //holder.date.setText(tt_List.get(position).getMeeting_DATE());
        holder.time.setText(tt_List.get(position).getMeeting_TIME());
        holder.com_name.setText(tt_List.get(position).getCommittee_NAME());
        holder.sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int pos= holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                }
                sublist[1]=ma.memberid;
                sublist[0]=tt_List.get(pos).getMeeting_DATE();
                sublist[2]=tt_List.get(pos).getTitle();
                sublist[3]=tt_List.get(pos).getType();
                Log.d("클릭이벤트", tt_List.get(pos).getTitle());

                insertData insert = new insertData();
                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/subscribe/push/"+sublist[3]+"?"+"date="+sublist[0]+"&id="+sublist[1]+"&title="+sublist[2], "4");
                //commMain cm =new commMain();


            }
        });

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
    public void setCm_List(ArrayList<totalDate> to_list) {
        notifyDataSetChanged();

    }
    public ArrayList<totalDate> getMg_List(){
        return tt_List;
    }
}