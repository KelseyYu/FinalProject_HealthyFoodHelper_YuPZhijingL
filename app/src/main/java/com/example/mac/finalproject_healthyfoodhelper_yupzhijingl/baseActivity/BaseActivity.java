package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.LoginActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class BaseActivity extends Activity {


    public FirebaseAuth mAuth;

    public FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.getInstance().addActivity(this);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if((currentUser == null  || currentUser.getUid() == null) &&
                (this.getClass().equals(LoginActivity.class) == false &&
                this.getClass().equals(RegisterActivity.class) == false)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if((currentUser == null  || currentUser.getUid() == null) &&
                (this.getClass().equals(LoginActivity.class) == false &&
                        this.getClass().equals(RegisterActivity.class) == false)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        ActivityCollector.getInstance().setLatestActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.getInstance().removeActivity(this);

        super.onDestroy();
    }

}
