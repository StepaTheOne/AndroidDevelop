package com.example.gson

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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


class MainActivity : AppCompatActivity(),ImageAdapter.CellClickListener {

    private val urlString = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
    private val photoUrls:ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
        }

        Timber.plant(Timber.DebugTree())

        val photosType = object : TypeToken<Wrapper>() {}.type

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

        }
        recyclerView.adapter = ImageAdapter(applicationContext,photoUrls, this)

    }
    private fun loadTextPhotos():String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(urlString)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return response.body!!.string()
        }

    }

    private fun openPicActivity(position: String){
        val intent: Intent = Intent(this,PicViewer::class.java)
        intent.putExtra("name",position)
        startActivity(intent)
    }

    override fun onCellClickListener(position: Int) {
        openPicActivity(photoUrls[position])
    }

}