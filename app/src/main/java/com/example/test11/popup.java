package com.example.test11;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class popup extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox born_checkbox;
    CheckBox gong_checkbox;
    CheckBox all_checkbox;
    CheckBox small_checkbox;
    CheckBox semi_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        born_checkbox = (CheckBox)findViewById(R.id.born);
        gong_checkbox = (CheckBox)findViewById(R.id.gong);
        all_checkbox = (CheckBox)findViewById(R.id.all);
        small_checkbox = (CheckBox)findViewById(R.id.small);
        semi_checkbox = (CheckBox)findViewById(R.id.semi);

        born_checkbox.setOnCheckedChangeListener(this);
        gong_checkbox.setOnCheckedChangeListener(this);
        all_checkbox.setOnCheckedChangeListener(this);
        small_checkbox.setOnCheckedChangeListener(this);
        semi_checkbox.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        Toast myToast = Toast.makeText(this.getApplicationContext(), "4개 선택", Toast.LENGTH_SHORT);

        if(born_checkbox.isChecked()) myToast.show();
        if(gong_checkbox.isChecked()) myToast.show();
        if(all_checkbox.isChecked()) myToast.show();
        if(small_checkbox.isChecked()) myToast.show();
        if(semi_checkbox.isChecked()) myToast.show();

    }

}
