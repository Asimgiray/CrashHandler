package com.example.crashhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCrash;
    Context ctxMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctxMain = this;
        Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler(this));

        btnCrash = findViewById(R.id.btnCrash);

        btnCrash.setOnClickListener(v -> {
            String s = null;
            Log.d("ThisLogWillCrashTheApp" , "" + s.length());
        });

    }
}