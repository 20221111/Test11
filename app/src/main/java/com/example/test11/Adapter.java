package  com.example.test11;
// https://3001ssw.tistory.com/201
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<commMain> cm_List;
    Activity activity;

    public Adapter(ArrayList<commMain> cm_List) {
        this.cm_List = cm_List;
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
        commMain cm = cm_List.get(position);
        //String imageurl=drugimage.getDrugImg();
        holder.title.setText(cm_List.get(position).getTitle());
        holder.date.setText(cm_List.get(position).getMeeting_DATE());
        holder.time.setText(cm_List.get(position).getMeeting_TIME());
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
        return cm_List.size();
    }
    public void setCm_List(commMain strData) {
        cm_List.add(strData);
    }
    public ArrayList<commMain> getCm_List(){
        return cm_List;
    }
}