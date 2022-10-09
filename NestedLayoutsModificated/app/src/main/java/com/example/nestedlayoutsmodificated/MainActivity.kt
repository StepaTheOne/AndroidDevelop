package com.example.nestedlayoutsmodificated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_plus:Button = findViewById(R.id.button_plus)
        val arrayText = arrayListOf<TextView>(findViewById(R.id.textView1))
        arrayText.add(findViewById(R.id.textView2))
        arrayText.add(findViewById(R.id.textView3))
        arrayText.add(findViewById(R.id.textView4))
        arrayText.add(findViewById(R.id.textView5))
        arrayText.add(findViewById(R.id.textView6))
        arrayText.add(findViewById(R.id.textView7))
        arrayText.add(findViewById(R.id.textView8))
        arrayText.add(findViewById(R.id.textView9))
        var count = 0
        button_plus.setOnClickListener {
            if(count == 2){
                arrayText[count].setText("")
                arrayText[count+3].setText("")
                arrayText[count+6].setText("")
                count = 0
                arrayText[count].setText("1")
                arrayText[count+3].setText("1")
                arrayText[count+6].setText("1")
            } else{
                val a = count+2
                arrayText[count].setText("")
                arrayText[count+3].setText("")
                arrayText[count+6].setText("")

                arrayText[count+1].setText(a.toString())
                arrayText[count+4].setText(a.toString())
                arrayText[count+7].setText(a.toString())
                count++
            }
        }
    }
}