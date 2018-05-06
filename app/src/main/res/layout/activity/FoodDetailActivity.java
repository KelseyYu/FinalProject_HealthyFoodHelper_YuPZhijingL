package com.healthy.food.helper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.healthy.food.helper.R;
import com.healthy.food.helper.layout.TitleLayout;

/**
 * @author bsl
 * @package com.healthy.food.helper.activity
 * @filename FoodDetailActivity
 * @date 18-4-25
 * @email don't tell you
 * @describe TODO
 */

public class FoodDetailActivity extends com.healthy.food.helper.baseActivity.BaseActivity {

    private static final String TAG = "FoodDetailActivity";

    private TitleLayout titleLayout;

    private ImageView imageView;

    private TextView nameTxt;

    private TextView carTxt;

    private TextView nutriTxt;

    private com.healthy.food.helper.data.Food food;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_detail);

        initWidgets();

        receiveData();

        initData();
    }

    // init interface
    private void initWidgets() {
        titleLayout = (TitleLayout) findViewById(R.id.detail_title);

        imageView   = (ImageView) findViewById(R.id.detail_img);

        nameTxt     = (TextView) findViewById(R.id.detail_name);

        carTxt      = (TextView) findViewById(R.id.detail_car);

        nutriTxt    = (TextView) findViewById(R.id.detail_nutri);
    }

    // receive data from SearchActivity
    private void receiveData() {
        Intent intent = getIntent();
        food    = (com.healthy.food.helper.data.Food)(intent.getSerializableExtra("food"));
    }

    // init data
    private void initData() {
        if(food == null) {
            return;
        }

        imageView.setBackgroundResource(food.getImgId());
        nameTxt.setText(food.getName());
        carTxt.setText(food.getCarloriesPerServing());
        nutriTxt.setText(food.getNutriion());
    }

}
