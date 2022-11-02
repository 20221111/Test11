package  com.example.test11;
// https://3001ssw.tistory.com/201
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<totalDate> tt_List;
    //List<?> mergedList;

    Activity activity;

    public Adapter1( ArrayList<totalDate> tt_list) {
        this.tt_List=tt_list;

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
        holder.date.setText(tt_List.get(position).getMeeting_DATE());
        holder.time.setText(tt_List.get(position).getMeeting_TIME());
        /*holder.date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction;
                DruginfoActivity fragmentDruginfo = new DruginfoActivity();
//                DrugItem drugItem = new DrugItem();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DrugItem", drugItemArrayList);
                bundle.putInt("position", position);
                fragmentDruginfo.setArguments(bundle);
                fragmentTransaction = fm.beginTransaction().add(R.id.menu_frame_layout, fragmentDruginfo);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });*/
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