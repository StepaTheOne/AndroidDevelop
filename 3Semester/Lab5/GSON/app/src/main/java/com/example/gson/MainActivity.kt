package com.example.gson

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import timber.log.Timber
import java.net.URL


class MainActivity : AppCompatActivity() {

    private val urlString = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        val photoType = object: TypeToken<Photo>() {}.type
        val photoPageType = object: TypeToken<PhotoPage>(){}.type
        val photosType = object : TypeToken<Wrapper>() {}.type

        val photoUrls:MutableList<String> = mutableListOf()
        val photoImg:MutableList<Bitmap> = mutableListOf()

        val res = GlobalScope.async {
            loadPhotos()
        }


        runBlocking {
            val photos = Gson().fromJson<Wrapper>(res.await(), photosType)
            var count = 0
            for(i in photos.photoPage.photo){
                photoUrls.add("https://farm${i.farm}.staticflickr.com/${i.server}/${i.id}_${i.secret}_z.jpg")
                if(count%5 == 0){
                    Timber.tag("Every 5").d(i.toString())
                }
                count++
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
    private fun loadPhotos():String {
        val basedUrl1 = URL(urlString)
        return basedUrl1.readText()
    }

    private fun GetPhotosImg(photoUrls:List<String>):MutableList<Bitmap> {
        val photosImg:MutableList<Bitmap> = mutableListOf()
        for(i in photoUrls){
            val basedUrl1 = URL(i).openStream()
            photosImg.add(BitmapFactory.decodeStream(basedUrl1))
        }
        return photosImg
    }

    private fun loadRandomFactt() {
        val basedUrl1 = URL(urlString)
        Timber.tag("AAAAAAAAAAAAA").d(basedUrl1.readText())
    }
}