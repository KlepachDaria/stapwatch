package com.example.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_stopwatch.*

class StopwatchActivity : AppCompatActivity() {

    var handler: Handler? = null
    var hour: TextView? = null
    var minute: TextView? = null
    var seconds: TextView? = null
    var milli_seconds: TextView? = null

    internal var MillisecondTime: Long = 0
    internal var StartTime: Long = 0
    internal var TimeBuff: Long = 0
    internal var UpdateTime = 0L


    internal var Seconds: Int = 0
    internal var Minutes: Int = 0
    internal var MilliSeconds: Int = 0





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        hour = findViewById(R.id.hour)
        minute = findViewById(R.id.minute)
        seconds = findViewById(R.id.seconds)
        milli_seconds = findViewById(R.id.milli_second)


        var runnable: Runnable = object : Runnable {

            override fun run() {

                MillisecondTime = SystemClock.uptimeMillis() - StartTime

                UpdateTime = TimeBuff + MillisecondTime

                Seconds = (UpdateTime / 1000).toInt()

                Minutes = Seconds / 60

                Seconds = Seconds % 60

                MilliSeconds = (UpdateTime % 1000).toInt()


                if (Minutes.toString().length < 2) {
                    minute?.text = "0" + Minutes.toString()
                } else {
                    minute?.text = Minutes.toString()
                }
                if (Seconds.toString().length < 2) {
                    seconds?.text = "0" + Seconds.toString()
                } else {
                    seconds?.text = Seconds.toString()
                }

                milli_seconds?.text = MilliSeconds.toString()

                handler?.postDelayed(this, 0)
            }

        }
        var stoptime: Long =0

        btnStart.setOnClickListener{
            StartTime = SystemClock.elapsedRealtime() + stoptime
            handler?.postDelayed(runnable, 0)
            btnStart.visibility = View.GONE
            btnPause.visibility = View.VISIBLE
        }

        handler = Handler()

        btnPause.setOnClickListener{
            stoptime = StartTime - SystemClock.elapsedRealtime()
            handler?.removeCallbacks(runnable)
            btnStart.visibility = View.VISIBLE
            btnPause.visibility = View.GONE
        }

        btnStop.setOnClickListener{
            StartTime = SystemClock.elapsedRealtime()
            hour?.text = "00"
            minute?.text = "00"
            seconds?.text = "00"
            milli_seconds?.text = "0"
            handler?.removeCallbacks(runnable)
            btnStart.visibility = View.VISIBLE
            btnPause.visibility = View.GONE

        }


    }
}
