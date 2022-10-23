package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button:Button = findViewById(R.id.buttonSave)
        val check:CheckBox = findViewById(R.id.checkBox)
        val progressBar:ProgressBar = findViewById(R.id.progressBar)
        val savedText:TextView = findViewById(R.id.textView)
        val textNeedtoSave:EditText = findViewById(R.id.editText)
        button.setOnClickListener {
            if(check.isChecked){
                if(progressBar.progress == 100){
                    progressBar.progress = 0
                }else{
                    progressBar.progress = progressBar.progress + 10
                }
                savedText.setText(textNeedtoSave.text)
            }
        }
    }
}