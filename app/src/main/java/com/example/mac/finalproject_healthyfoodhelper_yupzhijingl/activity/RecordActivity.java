package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity.BaseActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.MenuManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.RecordData;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TabLayoutManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TitleLayout;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.listener.TabClickListener;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.permission.ApplicationManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.LogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.ProgressDialogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.TimeHelper;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.UriUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class RecordActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private static final String TAG = "RecordActivity";

    private static final int IMAGE_REQUEST_CODE = 111;

    private TitleLayout titleLayout;

    private TabLayout tabLayout;

    private ImageView pictureImg;
    
    private EditText stapleEdt;

    private EditText otherEdt;

    private CheckBox multipleProblem;

    private CheckBox multipleCarbohy;

    private CheckBox multipleFat;

    private RadioGroup radioGroup;

    private EditText feelingEdt;

    private TextView submitBtn;

    private ImageView photoBtn;


    private RecordData recordData;

    private TabLayoutManager tabLayoutManager;


    private List<String> checkboxs;

    private String imgPath;

    private DatabaseReference mDatabase;

    private ProgressDialogUtil progressDialogUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initWidgets();

        initEnv();
    }

    // init global variables
    private void initEnv() {
        ApplicationManager.setContext(this);

        recordData  = new RecordData();
        checkboxs   = new ArrayList<String>();

        mDatabase   = FirebaseDatabase.getInstance().getReference();
        mAuth       = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        progressDialogUtil  = new ProgressDialogUtil(this);
    }

    // init interface
    private void initWidgets() {
        titleLayout     = (TitleLayout) findViewById(R.id.record_title);
        titleLayout.setBtnListener(this);

        tabLayout       = (TabLayout) findViewById(R.id.main_tab);
        tabLayoutManager= new TabLayoutManager(tabLayout, TabLayoutManager.TAB_QUESTION);
        tabLayout.addOnTabSelectedListener(new TabClickListener(this));

        pictureImg      = (ImageView) findViewById(R.id.record_picture);

        stapleEdt       = (EditText) findViewById(R.id.record_staple);
        otherEdt        = (EditText) findViewById(R.id.record_other);

        multipleProblem = (CheckBox) findViewById(R.id.record_multiple_problem);
        multipleCarbohy = (CheckBox) findViewById(R.id.record_multiple_carbohydrates);
        multipleFat     = (CheckBox) findViewById(R.id.record_multiple_fat);
        multipleProblem.setOnCheckedChangeListener(this);
        multipleCarbohy.setOnCheckedChangeListener(this);
        multipleFat.setOnCheckedChangeListener(this);

        radioGroup      = (RadioGroup) findViewById(R.id.record_radio_group);

        feelingEdt      = (EditText) findViewById(R.id.record_feeling);

        submitBtn       = (TextView) findViewById(R.id.record_submit);
        photoBtn        = (ImageView) findViewById(R.id.record_photo);

        submitBtn.setOnClickListener(this);
        photoBtn.setOnClickListener(this);
    }

    private boolean check(){
        // check radio
        int radioId = radioGroup.getCheckedRadioButtonId();
        if(radioId < 0){
            Toast.makeText(this, "Please select radio button before submit",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        String times = ((RadioButton) findViewById(radioId)).getText().toString();
        recordData.setTimes(times);

        // check checkbox
        if(checkboxs.size() <= 0) {
            Toast.makeText(this, "Please select checkbox before submit",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        recordData.setLargest(checkboxs);

        // check staple
        String staple = stapleEdt.getText().toString();
        if(staple == null || "".equals(staple)) {
            Toast.makeText(this, "Please input staple food before submit",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        recordData.setStaple(staple);

        // check other
        String other = otherEdt.getText().toString();
        if(other == null || "".equals(other)) {
            Toast.makeText(this, "Please input other food before submit",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        recordData.setFood(other);

        // check feeling
        String feeling = feelingEdt.getText().toString();
        if(feeling == null || "".equals(feeling)) {
            Toast.makeText(this, "Please input your feeling before submit",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        recordData.setFeeling(feeling);

        // check image
//        if(imgPath == null) {
//            Toast.makeText(this, "Please upload the image from your camera before submit",
//                    Toast.LENGTH_LONG).show();
//            return false;
//        }
        recordData.setImg(imgPath);

        recordData.setDate(TimeHelper.long2str(TimeHelper.getTime(),TimeHelper.FORMAT_MODE_DAY));

        return true;
    }

    // submit data to server
    private void submit() {
        if(check()) {
            progressDialogUtil.showProgressDialog("Save Question", "Saving....");
            LogUtil.i(TAG, recordData.toString());
            Task task = mDatabase.child("questions").child(currentUser.getUid()).push().setValue(recordData);
            task.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()) {
                        LogUtil.i(TAG, "save successfully");
                        Toast.makeText(RecordActivity.this, "save successfully", Toast.LENGTH_LONG).show();
                    } else {
                        LogUtil.e(TAG, "save recordData error");
                        Toast.makeText(RecordActivity.this, "save failed", Toast.LENGTH_LONG).show();
                    }

                    progressDialogUtil.hideProgressDialog();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_submit:
                submit();
                break;
            case R.id.record_photo:
                selectPhoto();
                break;
            case R.id.title_btn:
                MenuManager menuManager = new MenuManager(this, view);
                menuManager.showMenu();
                break;
            default:
                break;
        }
    }

    // select photo from album
    private void selectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String text = compoundButton.getText().toString();
        if(b) {
           LogUtil.i(TAG, "select " + text);
           checkboxs.add(text);
        } else {
           LogUtil.i(TAG, "disselect " + text);
           checkboxs.remove(text);
        }
    }

    // load image
    private void loadImage() {
        if(imgPath == null) {
            Toast.makeText(this, "Can't find image file", Toast.LENGTH_LONG).show();
            return;
        }

        Bitmap bm = BitmapFactory.decodeFile(imgPath);
        if(bm == null) {
            Toast.makeText(this, "Can't load image file", Toast.LENGTH_LONG).show();
            return;
        }

        pictureImg.setBackground(new BitmapDrawable(bm));
        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            imgPath = UriUtil.getAbsolutePathByUri(data.getData());
            loadImage();
        }

    }
}
