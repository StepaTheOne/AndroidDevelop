package com.example.internettest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

private const val TAG = "HTTPCONNETIONFINE"
private const val IsDebug = true

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url: URL = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
        val urlText: String = url.readText()

        val buttonHTTP: Button = findViewById(R.id.btnHTTP)
        buttonHTTP.setOnClickListener{
            if(IsDebug){
                Log.d(TAG,urlText)
            }
        }
        
    }
}