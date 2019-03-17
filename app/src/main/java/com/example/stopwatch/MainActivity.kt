package com.example.stopwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.os.SystemClock
import android.widget.Button
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity




class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var stoptime: Long = 0

        start_button.setOnClickListener{
            display.base = SystemClock.elapsedRealtime()+stoptime
            display.start()
            start_button.visibility = View.GONE
            pause_button.visibility = View.VISIBLE
        }

        pause_button.setOnClickListener{
            stoptime = display.base - SystemClock.elapsedRealtime()
            display.stop()
            pause_button.visibility = View.GONE
            start_button.visibility = View.VISIBLE
        }

        stop_button.setOnClickListener{
            display.base = SystemClock.elapsedRealtime()
            stoptime = 0
            display.stop()
            pause_button.visibility = View.GONE
            start_button.visibility = View.VISIBLE

        }

        nextTimer.onClick {
            longToast("Timer Milliseconds")
            startActivity<StopwatchActivity>()
        }



    }



}




