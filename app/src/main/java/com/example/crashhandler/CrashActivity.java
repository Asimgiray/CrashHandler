package com.example.crashhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashMap;

public class CrashActivity extends AppCompatActivity {

    private static HashMap<String, String> errorMap = new HashMap<>();
    TextView txtErrorInfo;
    Button btnRestartApp;
    StringBuilder errorInfo = new StringBuilder();
    Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);

        act = this;

        errorMap = (HashMap<String, String>) getIntent().getSerializableExtra("ErrorMessage");

        btnRestartApp = findViewById(R.id.btnRestartApp);
        txtErrorInfo = findViewById(R.id.txtErrorInfo);

        btnRestartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.finishAffinity();
            }
        });

        errorInfo.append("Cause of Error:" + "\n");
        errorInfo.append(errorMap.get("cause_of_error") + "\n");
        errorInfo.append("SofwareInfo:" + "\n");
        errorInfo.append(errorMap.get("software_information")+ "\n");
        errorInfo.append("Date:" + "\n");
        errorInfo.append(errorMap.get("date"));

        txtErrorInfo.setText(errorInfo.toString());




    }
}