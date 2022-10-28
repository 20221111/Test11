package com.example.test11;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Create_Account extends AppCompatActivity {
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        joinmember jm=new joinmember();

        /*String id=입력아이디;
        String password=입력;
        String email=입력;
        String name=입력;
        String security=입력;*/

        //클릭 버튼 생겨야하고
        //클릭 버튼 이후

       /* jm.setId(id);
        jm.setEmail(email);
        jm.setName(name);
        jm.setPassword(password);
        jm.setSecurity(security);*/

        /*insertData insert = new insertData();
        insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/SignUp/"+jm.getId()+"?", "0");*/

    }

}
