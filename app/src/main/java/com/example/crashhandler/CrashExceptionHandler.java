package com.example.crashhandler;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.HashMap;

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {

    private StringBuilder errorMessage = new StringBuilder();
    private StringBuilder softwareInfo = new StringBuilder();
    private StringBuilder dateInfo = new StringBuilder();

    private HashMap<String , String> errorMap = new HashMap<>();
    private final String LINE_SEPARATOR = "\n";
    private Context context;

    public CrashExceptionHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable exception) {

        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));

        errorMessage.append(stackTrace.toString());

        softwareInfo.append("SDK: ");
        softwareInfo.append(Build.VERSION.SDK);
        softwareInfo.append(LINE_SEPARATOR);
        softwareInfo.append("Release: ");
        softwareInfo.append(Build.VERSION.RELEASE);
        softwareInfo.append(LINE_SEPARATOR);
        softwareInfo.append("Incremental: ");
        softwareInfo.append(Build.VERSION.INCREMENTAL);
        softwareInfo.append(LINE_SEPARATOR);

        dateInfo.append(Calendar.getInstance().getTime());
        dateInfo.append(LINE_SEPARATOR);

        errorMap.put("cause_of_error" , errorMessage.toString());
        errorMap.put("software_information" , softwareInfo.toString());
        errorMap.put("date" , dateInfo.toString());
        Log.d("TAG" , errorMap.toString());

        Intent intent = new Intent(context , CrashActivity.class);
        intent.putExtra("ErrorMessage" , errorMap);
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(2);


    }
}
