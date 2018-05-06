package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.listener;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.ApplicationActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.LoginActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.SelfActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity.ActivityCollector;
import com.google.firebase.auth.FirebaseAuth;


public class MenuClickListener implements PopupMenu.OnMenuItemClickListener {

    private Context context;

    private FirebaseAuth auth;

    public MenuClickListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_self:
                startActivity(SelfActivity.class);
                break;
            case R.id.action_application:
                startActivity(ApplicationActivity.class);
                break;
            case R.id.action_account:
                signOut();
                startActivity(LoginActivity.class);
                break;
            case R.id.action_signout:
                signOut();
                ActivityCollector.getInstance().finishAll();
                break;
        }
        return false;
    }

    // sign out
    private void signOut() {
        auth = FirebaseAuth.getInstance();
        auth.signOut();
    }

    // start activity
    private void startActivity(Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

}
