package com.healthy.food.helper.baseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.healthy.food.helper.activity.LoginActivity;
import com.healthy.food.helper.activity.RegisterAcivity;

/**
 * @author bsl
 * @package little.boy.bsl.baseactivity.util
 * @filename BaseActivity
 * @date 18-4-18
 * @email don't tell you
 * @describe TODO
 */

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
                this.getClass().equals(RegisterAcivity.class) == false)) {
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
                        this.getClass().equals(RegisterAcivity.class) == false)) {
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
