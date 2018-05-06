package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.listener;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.HistoryActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.RecordActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.SearchActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TabLayoutManager;

public class TabClickListener implements TabLayout.OnTabSelectedListener {

    private Context context;

    public TabClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        final int tag = (Integer)(tab.getTag());
        switch (tag) {
            case TabLayoutManager.TAB_QUESTION:
                startActivity(RecordActivity.class);
                break;
            case TabLayoutManager.TAB_SEARCH:
                startActivity(SearchActivity.class);
                break;
            case TabLayoutManager.TAB_RECORDS:
                startActivity(HistoryActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    private void startActivity(Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
