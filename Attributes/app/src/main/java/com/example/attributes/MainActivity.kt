package com.example.attributes

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myText: EditText = findViewById(R.id.myEditText)
        val button_bl: Button = findViewById(R.id.button_black)
        val button_rd: Button = findViewById(R.id.button_red)
        val button_eight: Button = findViewById(R.id.button_8sp)
        val button_twnt: Button = findViewById(R.id.button24sp)
        val button_yeBack: Button = findViewById(R.id.button_yellowBack)
        val button_whBack: Button = findViewById(R.id.button_whiteBack)
        button_bl.setOnClickListener {
            myText.setTextColor(Color.BLACK)
        }
        button_rd.setOnClickListener {
            myText.setTextColor(Color.RED)
        }
        button_eight.setOnClickListener {
            myText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8F)
        }
        button_twnt.setOnClickListener {
            myText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
        }
        button_yeBack.setOnClickListener {
            myText.setBackgroundColor(Color.YELLOW)
        }
        button_whBack.setOnClickListener {
            myText.setBackgroundColor(Color.WHITE)
        }
    }
}