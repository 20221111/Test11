package com.example.test11;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*public class DialogFragment extends DialogFragment {

    private Fragment fragment;
    public FragmentDialog() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_search,container,false);
        Button open = (Button) v.findViewById(R.id.btn_ft);

        open.setOnClickListener(this);

        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openDialogFragment:
                Bundle args = new Bundle();
                args.putString("key", "value");
                DialogFragment dialog = new DialogFragment();
                dialog.setArguments(args); // 데이터 전달
                dialog.show(getActivity().getSupportFragmentManager(),"tag");
                break;}
        }
}*/