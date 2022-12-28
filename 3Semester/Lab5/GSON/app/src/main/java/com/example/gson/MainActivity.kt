package com.example.gson

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import timber.log.Timber
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.*


class MainActivity : AppCompatActivity() {

    private val urlString = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())


        val photosType = object : TypeToken<Wrapper>() {}.type

        val photoUrls:MutableList<String> = mutableListOf()

        val res = GlobalScope.async {
            loadTextPhotos()
        }


        runBlocking {
            val photos = Gson().fromJson<Wrapper>(res.await(), photosType)
            for((count, i) in photos.photoPage.photo.withIndex()){
                photoUrls.add("https://farm${i.farm}.staticflickr.com/${i.server}/${i.id}_${i.secret}_z.jpg")
                if(count%5 == 0){
                    Timber.tag("Every 5").d(i.toString())
                }
            }

            val photoImg = GlobalScope.async {
                GetPhotosImg(photoUrls)
            }

            val recyclerView: RecyclerView = findViewById(R.id.rView)
            recyclerView.apply {
                layoutManager = GridLayoutManager(applicationContext, 2)
            }
            recyclerView.adapter = ImageAdapter(applicationContext,photoImg.await())

        }
        /*val button: Button = findViewById(R.id.button)
        button.setOnClickListener{

            val res = GlobalScope.async {
                loadPhotos()
            }

            runBlocking {
                val photos = Gson().fromJson<Wrapper>(res.await(), photosType)
                var count = 0
                for(i in photos.photoPage.photo){
                    if(count%5 == 0){
                        Timber.tag("FFFFFFFFFFFFF").d(i.toString())
                    }
                    count++
                }
            }
        }*/


    }
    private fun loadTextPhotos():String {
        //val basedUrl1 = URL(urlString)
        //return basedUrl1.readText()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(urlString)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return response.body!!.string()
        }

    }

    private fun GetPhotosImg(photoUrls:List<String>):MutableList<Bitmap> {
        val photosImg:MutableList<Bitmap> = mutableListOf()
        val client = OkHttpClient()

        for(i in photoUrls){
            try{
                val request = Request.Builder().url(i)
                    .build()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val imageTemp = response.body?.byteStream()
                    photosImg.add(BitmapFactory.decodeStream(imageTemp))

                }
            }
            catch(e:IOException){

            }
        }
        return photosImg
/*
        for(i in photoUrls){
            val basedUrl1 = URL(i).openStream()
            photosImg.add(BitmapFactory.decodeStream(basedUrl1))
        }
        return photosImg*/
    }

    private fun loadRandomFactt() {
        val basedUrl1 = URL(urlString)
        Timber.tag("AAAAAAAAAAAAA").d(basedUrl1.readText())
    }

}