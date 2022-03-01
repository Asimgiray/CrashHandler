package com.example.crashhandler

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.crashhandler.databinding.ActivityMainBinding
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val context: Context = this

        Thread.setDefaultUncaughtExceptionHandler(CrashHandler(context))

        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {
                binding.txtCountDown.text = resources.getString(R.string.count_down, p0/1000)
            }
            override fun onFinish() {
                throw NullPointerException()
            }
        }
        timer.start()

    }
}