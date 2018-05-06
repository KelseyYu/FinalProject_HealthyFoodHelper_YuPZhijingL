package com.healthy.food.helper.data;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

import com.healthy.food.helper.R;

/**
 * @author bsl
 * @package com.healthy.food.helper.data
 * @filename MenuManager
 * @date 18-4-26
 * @email don't tell you
 * @describe TODO
 */

public class MenuManager {

    private static final String TAG = "MenuManager";

    private Context context;

    private PopupMenu popupMenu;

    public MenuManager(Context context, View view) {
        this.context = context;
        popupMenu = new PopupMenu(context, view);
    }

    public void showMenu() {
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new com.healthy.food.helper.listener.MenuClickListener(this.context));
        popupMenu.show();
    }

}
