package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.baseActivity.BaseActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TitleLayout;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.permission.ApplicationManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.permission.PermissionCheckThread;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.permission.PermissionManager;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.LogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.ProgressDialogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.VerificationUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";

    private EditText userNameEdt;

    private EditText passWordEdt;

    private TextView loginBtn;

    private TitleLayout titleBtn;

    private ProgressDialogUtil progressDialogUtil;


    private String[] permissions    = {Manifest.permission.READ_EXTERNAL_STORAGE};

    private String[] interpres      = {"文件存储"};

    private PermissionCheckThread checkThread;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initWidgets();

        ApplicationManager.setContext(this);

        PermissionManager.requestPermission(permissions, interpres);
        checkThread = new PermissionCheckThread(handler, permissions);
        checkThread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case PermissionCheckThread.PERMISSION_CHECK:
                    flag = true;
                    initEnv();
                    break;
                default:
                    break;
            }
        }
    };

    // init interface
    private void initWidgets() {
        titleBtn       = (TitleLayout) findViewById(R.id.login_title);
        titleBtn.setBtnListener(this);

        userNameEdt    = (EditText) findViewById(R.id.login_input_name);

        passWordEdt    = (EditText) findViewById(R.id.login_input_psw);

        loginBtn       = (TextView) findViewById(R.id.main_btn_login);

        loginBtn.setOnClickListener(this);
    }

    // init global variables
    private void initEnv() {
        progressDialogUtil = new ProgressDialogUtil(this);

        mAuth       = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        if(flag && currentUser != null && currentUser.getUid() != null){
            startApplication();
        }
    }

    // start application
    private void startApplication() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }

    // sign in
    private void signIn(String email, String password) {

        if(VerificationUtil.verifyEmail(email) == false) {
            Toast.makeText(this, "email is invalid", Toast.LENGTH_LONG).show();
            return;
        }

        if(VerificationUtil.verifyNormal(password,8) == false) {
            Toast.makeText(this, "password length at least 8", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialogUtil.showProgressDialog("Login tips", "Login...");

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LogUtil.d(TAG, "sign in with email successfully");
                            startApplication();
                            //currentUser = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            LogUtil.w(TAG, "sign in with email failed: " + task.getException());
                            Toast.makeText(LoginActivity.this, "Login Failed",
                                    Toast.LENGTH_LONG).show();
                        }

                progressDialogUtil.hideProgressDialog();
                    }
                });
    }

    // goto sign up
    private void startRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
       if(!flag) {
            Toast.makeText(this, "Please authorize for application first",
                    Toast.LENGTH_LONG).show();
            return;
       }

        switch (view.getId()) {
            case R.id.main_btn_login:
                LogUtil.i(TAG, "click sign in button");
                signIn(userNameEdt.getText().toString(), passWordEdt.getText().toString());
                break;
            case R.id.title_btn:
                LogUtil.i(TAG, "click sign up button");
                startRegister();
                break;
            default:
                break;
        }
    }

}
