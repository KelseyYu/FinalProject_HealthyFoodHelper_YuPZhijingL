package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.layout.TitleLayout;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.LogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.ProgressDialogUtil;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.VerificationUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterAcivity";
    
    private TitleLayout titleLayout;

    private EditText emailTxt;

    private EditText passwordTxt;

    private EditText passwordAgainTxt;

    private TextView registerBtn;


    private FirebaseAuth mAuth;

    private ProgressDialogUtil progressDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initWidgets();

        initEnv();
    }

    // init interface
    private void initWidgets() {
        titleLayout     = (TitleLayout) findViewById(R.id.register_title);

        emailTxt        = (EditText) findViewById(R.id.register_input_name);

        passwordTxt     = (EditText) findViewById(R.id.register_input_psw);

        passwordAgainTxt= (EditText) findViewById(R.id.register_input_psw_again);

        registerBtn     = (TextView) findViewById(R.id.main_btn_register);

        registerBtn.setOnClickListener(this);
    }

    // init global variables
    private void initEnv() {
        mAuth = FirebaseAuth.getInstance();

        progressDialogUtil = new ProgressDialogUtil(this);
    }

    // sign up
    private void signUp(String email, String password, String password2) {
        if(VerificationUtil.verifyEmail(email) == false) {
            Toast.makeText(this, "email is invalid", Toast.LENGTH_LONG).show();
            return;
        }

        if(VerificationUtil.verifyNormal(password, 8) == false) {
            Toast.makeText(this, "password length at least 8", Toast.LENGTH_LONG).show();
            return;
        }

        if(password.equals(password2) == false) {
            Toast.makeText(this, "Two password inconsistencies", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialogUtil.showProgressDialog("Register tips", "Register...");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LogUtil.d(TAG, "create user with email successfully");

                            startApplication();
                        } else {
                            LogUtil.w(TAG, "create user with email failed:" + task.getException());
                            Toast.makeText(RegisterActivity.this, "Register Failed",
                                    Toast.LENGTH_LONG).show();
                        }

                        progressDialogUtil.hideProgressDialog();
                    }
                });
    }

    // start application
    private void startApplication() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_register:
                LogUtil.i(TAG, "click register button");
                signUp(emailTxt.getText().toString(), passwordTxt.getText().toString(),
                        passwordAgainTxt.getText().toString());
                break;
            default:
                break;
        }
    }
}
