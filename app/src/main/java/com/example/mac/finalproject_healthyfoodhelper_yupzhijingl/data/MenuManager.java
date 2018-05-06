package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.listener.MenuClickListener;



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
        popupMenu.setOnMenuItemClickListener(new MenuClickListener(this.context));
        popupMenu.show();
    }

}
