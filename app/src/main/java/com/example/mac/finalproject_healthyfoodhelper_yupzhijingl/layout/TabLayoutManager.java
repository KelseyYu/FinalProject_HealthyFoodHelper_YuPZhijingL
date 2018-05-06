package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class TabLayoutManager {

    private final String TAG = "TabLayout";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TAB_QUESTION, TAB_SEARCH, TAB_RECORDS})
    public @interface Tab {}
    public static final int TAB_QUESTION    = 0;
    public static final int TAB_SEARCH      = 1;
    public static final int TAB_RECORDS     = 2;

    // image in tab
    private int[] resId      = { R.mipmap.paw_code, R.mipmap.paw_left, R.mipmap.project_detail_cir};

    // name in tab
    private String[] tabName =  {"QUESTION", "Search", "Record"};

    private int[]   tabTag   =  {TAB_QUESTION, TAB_SEARCH, TAB_RECORDS};

    private TabLayout tabLayout;

    private int number;


    public TabLayoutManager (TabLayout tabLayout, @Tab int number) {
        this.tabLayout  = tabLayout;
        this.number     = number;

        setTabLayout();
    }

    private void setTabLayout() {
        for(int i = 0; i < tabName.length; i ++) {
            if(i == number) {
                tabLayout.addTab(tabLayout.newTab().setText(tabName[i]).setTag(tabTag[i]), true);
            } else {
                tabLayout.addTab(tabLayout.newTab().setText(tabName[i]).setTag(tabTag[i]), false);
            }
        }
    }


    // get a tab view by position
    public View getTabView(Context context, int pos) {
        View view           = LayoutInflater.from(context).inflate(R.layout.layout_tab,null);

        ImageView tabImg    = (ImageView) view.findViewById(R.id.tab_image);
        tabImg.setImageResource(resId[pos]);

        TextView tabTxt = (TextView) (view.findViewById(R.id.tab_text));
        tabTxt.setText(tabName[pos]);

        return view;
    }

}
