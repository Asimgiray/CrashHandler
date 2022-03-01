package com.example.crashhandler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.crashhandler.databinding.ActivityCrashBinding
import kotlin.system.exitProcess


class CrashActivity : AppCompatActivity() {

    lateinit var crashBinding: ActivityCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)

        crashBinding = DataBindingUtil.setContentView(this , R.layout.activity_crash)

        val error: String? = intent.getStringExtra("Error")
        val software: String? = intent.getStringExtra("Software")
        val date: String? = intent.getStringExtra("Date")

        crashBinding.txtCrash.text = error.plus(software).plus(date)

        crashBinding.btnGoBack.setOnClickListener {
            this.finishAffinity()
        }
    }
}