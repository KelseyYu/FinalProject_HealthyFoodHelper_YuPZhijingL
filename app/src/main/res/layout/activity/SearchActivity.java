package com.healthy.food.helper.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import com.healthy.food.helper.R;
import com.healthy.food.helper.data.FoodGetter;
import com.healthy.food.helper.layout.TitleLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends com.healthy.food.helper.baseActivity.BaseActivity implements View.OnClickListener{

    private static final String TAG = "SearchActivity";

    private TitleLayout titleLayout;

    private TabLayout tabLayout;

    private SearchView inputSV;

    private TextView searchBtn;

    private RecyclerView recyclerView;

    private com.healthy.food.helper.adapter.FoodAdapter foodAdapter;

    private FoodGetter foodGetter;

    private com.healthy.food.helper.layout.TabLayoutManager tabLayoutManager;

    private List<com.healthy.food.helper.data.Food> foodList = new ArrayList<com.healthy.food.helper.data.Food>();

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
        tabLayoutManager = new com.healthy.food.helper.layout.TabLayoutManager(tabLayout, com.healthy.food.helper.layout.TabLayoutManager.TAB_SEARCH);
        tabLayout.addOnTabSelectedListener(new com.healthy.food.helper.listener.TabClickListener(this));

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
        foodGetter  = new com.healthy.food.helper.data.FoodManager();
        foodList    = foodGetter.getFoodList();
        foodAdapter = new com.healthy.food.helper.adapter.FoodAdapter(foodList);
        recyclerView.setAdapter(foodAdapter);
    }

    // search
    private void search(String query) {

        List<com.healthy.food.helper.data.Food> list_remove = new ArrayList<com.healthy.food.helper.data.Food>();
        for(com.healthy.food.helper.data.Food food : foodGetter.getFoodList()) {
            if(food.getName().contains(query) == true) {
                list_remove.add(food);
            }
        }

        foodList.clear();

        for(com.healthy.food.helper.data.Food food : list_remove) {
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
                com.healthy.food.helper.data.MenuManager menuManager = new com.healthy.food.helper.data.MenuManager(this, view);
                menuManager.showMenu();
                break;
            default:
                break;
        }
    }
}
