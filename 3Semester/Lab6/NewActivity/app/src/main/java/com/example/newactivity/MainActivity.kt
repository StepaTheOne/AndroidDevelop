package com.example.newactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btn_show_pic)

        btn.setOnClickListener{
            openPicActivity()
        }
    }

    private fun openPicActivity(){
        val intent: Intent = Intent(this,PicActivity::class.java)
        startActivity(intent)
    }
}