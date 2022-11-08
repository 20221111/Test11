package com.example.test11;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class user_login_info extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_info);

        ImageButton enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CLD.class);
                startActivity(intent);
            }
        });
    }
}