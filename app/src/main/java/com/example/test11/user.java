package com.example.test11;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class user extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_user,container,false);

            //New account 클릭시 sign up 페이지로 이동
            Button NewAccount = v.findViewById(R.id.NewAccount);
            NewAccount.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent intent = new Intent(getActivity().getApplicationContext(), Create_Account.class);
                    startActivity(intent);
                }
            });
            //ForgotPassword 클릭시 forgotpassword 페이지로 이동
            Button ForgotPassword = v.findViewById(R.id.ForgotPassword);
            ForgotPassword.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent intent = new Intent(getActivity().getApplicationContext(), Forgot_Password.class);
                    startActivity(intent);
                }
            });

            ImageButton Sign_in = v.findViewById(R.id.Sign_in);
            Sign_in.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Intent intent = new Intent(getActivity().getApplicationContext(), CLD.class);
                    startActivity(intent);
                }
            });

        return v;
    }

}