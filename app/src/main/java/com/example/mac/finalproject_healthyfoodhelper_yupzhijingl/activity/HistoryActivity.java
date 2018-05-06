package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.adapter.HistoryAdapter;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity.BaseActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.MenuManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.RecordData;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TitleLayout;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.LogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.ProgressDialogUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HistoryActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HistoryActivity";

    private static final int UPDATE_RECORD = 999;

    private TitleLayout titleLayout;

    private RecyclerView recyclerView;

    private TextView tipsTxt;

    private List<RecordData> historys;

    private ProgressDialogUtil progressDialogUtil;

    private HistoryAdapter historyAdapter;

    private DatabaseReference mQuestionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initWidgets();

        initEnv();

        initDatas();
    }

    // init interface
    private void initWidgets() {
        tipsTxt		= (TextView) findViewById(R.id.history_tips);

        titleLayout = (TitleLayout) findViewById(R.id.history_title);
        titleLayout.setBtnListener(this);

        recyclerView= (RecyclerView) findViewById(R.id.history_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    // init global variables
    private void initEnv() {
        historys = new ArrayList<RecordData>();
        mQuestionReference = FirebaseDatabase.getInstance().getReference()
                .child("questions").child(currentUser.getUid());

        progressDialogUtil = new ProgressDialogUtil(this);

        mQuestionReference.addValueEventListener(postListener);
    }

    // init Question Record Datas
    private void initDatas() {

        historyAdapter	= new HistoryAdapter(historys);
        recyclerView.setAdapter(historyAdapter);
        progressDialogUtil.showProgressDialog("Load Data Tips", "Loading...");
        //progressDialogUtil.hideProgressDialog();

        //setTips();
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case UPDATE_RECORD:
                    progressDialogUtil.hideProgressDialog();
                    historyAdapter.notifyDataSetChanged();
                    setTips();
                    break;
                default:
                    break;
            }
        }
    };


    // set tips value
    private void setTips() {
        int size = historys.size();
        if(size <= 0) {
            tipsTxt.setText("There isn't any question record");
        } else {
            tipsTxt.setText("There are " + size + " question records");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_btn:
                MenuManager menuManager = new MenuManager(this, view);
                menuManager.showMenu();
                break;
            default:
                break;
        }
    }

    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            LogUtil.i(TAG, "childrenCount: "+dataSnapshot.getChildrenCount());

            Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
            Iterator iterator               = iterable.iterator();

            while(iterator.hasNext()) {
                DataSnapshot snapshot       = (DataSnapshot) (iterator.next());
                RecordData recordData       = snapshot.getValue(RecordData.class);
                historys.add(recordData);
                LogUtil.i(TAG, "onDataChange: img is " + recordData.getImg() + ", date is "
                        + recordData.getDate() + ", staple is " + recordData.getStaple());
            }

            Message msg     = new Message();
            msg.what       = UPDATE_RECORD;
            myHandler.sendMessage(msg);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };





}
