package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity.BaseActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.RecordData;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TitleLayout;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.CommonUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.ImageUtil;

/**
 * Created by mac on 2018/5/5.
 */

public class HistoryDetailActivity extends BaseActivity {

    private static final String TAG = "HistoryDetailActivity";

    private TitleLayout titleLayout;

    private ImageView imageView;

    private TextView dateTxt;

    private TextView stapleTxt;

    private TextView otherTxt;

    private TextView multipleTxt;

    private TextView radioTxt;

    private TextView feelingTxt;

    private RecordData history;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.history_detail);

        initWidgets();

        receiveData();

        initData();
    }

    // init interface
    private void initWidgets() {
        titleLayout = (TitleLayout) findViewById(R.id.history_detail_title);

        imageView   = (ImageView) findViewById(R.id.history_detail_img);

        dateTxt     = (TextView) findViewById(R.id.history_detail_date);

        stapleTxt   = (TextView) findViewById(R.id.history_detail_staple);

        otherTxt    = (TextView) findViewById(R.id.history_detail_other);

        multipleTxt	= (TextView) findViewById(R.id.history_detail_multiple);

        radioTxt	= (TextView) findViewById(R.id.history_detail_radio);

        feelingTxt	= (TextView) findViewById(R.id.history_detail_feeling);
    }

    // receive data from SearchActivity
    private void receiveData() {
        Intent intent = getIntent();
        history    = (RecordData)(intent.getSerializableExtra("history"));
    }

    // init data
    private void initData() {
        if(history == null) {
            return;
        }

        ImageUtil.setImageBg(imageView, history.getImg(), R.mipmap.healthy_food);

        dateTxt.setText(history.getDate());
        stapleTxt.setText(history.getStaple());
        otherTxt.setText(history.getFood());
        multipleTxt.setText(CommonUtil.list2String(history.getLargest(), "\n"));
        radioTxt.setText(history.getTimes());
        feelingTxt.setText(history.getFeeling());
    }

}
