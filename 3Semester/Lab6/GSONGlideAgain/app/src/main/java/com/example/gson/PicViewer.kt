package com.example.gson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import com.bumptech.glide.Glide
var url = ""
class PicViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_viewer)

        val toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Photo"

        val image:ImageView = findViewById(R.id.imageView2)

        url = intent.getStringExtra("name")!!
        Glide.with(this).load(url).into(image)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favor -> {intent.putExtra("name",url)
            setResult(RESULT_OK,intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}