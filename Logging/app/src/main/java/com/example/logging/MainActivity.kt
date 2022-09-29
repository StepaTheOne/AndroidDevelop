package com.example.logging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_log: Button = findViewById(R.id.button_log)
        val ext: EditText = findViewById(R.id.EditTextLol)
        button_log.setOnClickListener {
            Log.v("Entered_Text",ext.text.toString())
        }
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        val button_tim: Button = findViewById(R.id.button_timber)
        button_tim.setOnClickListener {
            Timber.v(ext.text.toString())
        }
    }
}