package com.example.applyingfilter


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.SeekBar



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.setOnSeekBarChangeListener(seekBarChangeListener)

        btn.setOnClickListener {
           filter.setImageResource(R.drawable.red1)
        }
        btn1.setOnClickListener {
            filter.setImageResource(R.drawable.red2)
        }
        btn2.setOnClickListener {
            filter.setImageResource(R.drawable.red3)
        }
    }

    var seekBarChangeListener: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            //var progressf: Double =progress * 1.0
            Log.e("hahaha","$progress")
            filter.alpha= progress/100F
            // tvProgressLabel.setText("Progress: $progress")
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // called when the user first touches the SeekBar
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // called after the user finishes moving the SeekBar
        }
    }

}

