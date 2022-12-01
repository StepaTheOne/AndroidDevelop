package com.example.internettest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Runnable
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URL


private const val TAG = "Flickr cats"
private const val TAG1 = "Flickr OkCats"
private const val IsDebug = true

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonHTTP: Button = findViewById(R.id.btnHTTP)
        val buttonOkHTTP: Button = findViewById(R.id.btnOkHTTP)

        val client = OkHttpClient()

        fun runCats(){
            val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
            Log.d(TAG,url.readText())
        }

        fun runOkCats() {
            val request = Request.Builder()
                .url("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
                //.url("https://api.chucknorris.io/jokes/random")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                Log.i(TAG1,response.body?.string()!!)
            }
        }

        buttonHTTP.setOnClickListener(){
            Thread (Runnable {
                runCats()
            }).start()
        }

        buttonOkHTTP.setOnClickListener(){
            /*GlobalScope.async{
                runOkCats()
            }*/
            Thread (Runnable {
                runOkCats()
            }).start()
        }

    }

}