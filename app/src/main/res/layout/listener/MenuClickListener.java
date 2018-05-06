package com.healthy.food.helper.listener;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.google.firebase.auth.FirebaseAuth;
import com.healthy.food.helper.R;
import com.healthy.food.helper.activity.ApplicationActivity;
import com.healthy.food.helper.activity.LoginActivity;
import com.healthy.food.helper.activity.SelfActivity;
import com.healthy.food.helper.baseActivity.ActivityCollector;

/**
 * @author bsl
 * @package com.healthy.food.helper.listener
 * @filename MenuClickListener
 * @date 18-4-26
 * @email don't tell you
 * @describe TODO
 */

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
