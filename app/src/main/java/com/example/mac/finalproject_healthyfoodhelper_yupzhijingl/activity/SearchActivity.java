package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;


import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.adapter.FoodAdapter;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity.BaseActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.Food;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.FoodGetter;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.FoodManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.MenuManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TabLayoutManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TitleLayout;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.listener.TabClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "SearchActivity";

    private TitleLayout titleLayout;

    private TabLayout tabLayout;

    private SearchView inputSV;

    private TextView searchBtn;

    private RecyclerView recyclerView;

    private FoodAdapter foodAdapter;

    private FoodGetter foodGetter;

    private TabLayoutManager tabLayoutManager;

    private List<Food> foodList = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initWidgets();

        initAllData();
    }


    // init interface
    private void initWidgets() {
        titleLayout = (TitleLayout) findViewById(R.id.search_title);
        titleLayout.setBtnListener(this);

        tabLayout   = (TabLayout) findViewById(R.id.main_tab);
        tabLayoutManager = new TabLayoutManager(tabLayout, TabLayoutManager.TAB_SEARCH);
        tabLayout.addOnTabSelectedListener(new TabClickListener(this));

        inputSV     = (SearchView) findViewById(R.id.search_input);

        searchBtn   = (TextView) findViewById(R.id.search_search_btn);

        recyclerView= (RecyclerView) findViewById(R.id.search_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        searchBtn.setOnClickListener(this);
    }

    // init data
    private void initAllData() {
        foodGetter  = new FoodManager();
        foodList    = foodGetter.getFoodList();
        foodAdapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);
    }

    // search
    private void search(String query) {

        List<Food> list_remove = new ArrayList<Food>();
        for(Food food : foodGetter.getFoodList()) {
            if(food.getName().contains(query) == true) {
                list_remove.add(food);
            }
        }

        foodList.clear();

        for(Food food : list_remove) {
            foodList.add(food);
        }

        foodAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_search_btn:
                search(inputSV.getQuery().toString());
                break;
            case R.id.title_btn:
                MenuManager menuManager = new MenuManager(this, view);
                menuManager.showMenu();
                break;
            default:
                break;
        }
    }
}
