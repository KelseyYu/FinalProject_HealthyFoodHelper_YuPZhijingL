package com.healthy.food.helper.listener;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import com.healthy.food.helper.activity.HistoryActivity;
import com.healthy.food.helper.activity.SearchActivity;

/**
 * @author bsl
 * @package com.healthy.food.helper.listener
 * @filename TabClickListener
 * @date 18-4-25
 * @email don't tell you
 * @describe TODO
 */

public class TabClickListener implements TabLayout.OnTabSelectedListener {

    private Context context;

    public TabClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        final int tag = (Integer)(tab.getTag());
        switch (tag) {
            case com.healthy.food.helper.layout.TabLayoutManager.TAB_QUESTION:
                startActivity(com.healthy.food.helper.activity.RecordActivity.class);
                break;
            case com.healthy.food.helper.layout.TabLayoutManager.TAB_SEARCH:
                startActivity(SearchActivity.class);
                break;
            case com.healthy.food.helper.layout.TabLayoutManager.TAB_RECORDS:
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
