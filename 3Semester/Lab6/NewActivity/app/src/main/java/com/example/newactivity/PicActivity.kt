package com.example.newactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.renderscript.ScriptGroup.Binding
import android.widget.Button
import com.bumptech.glide.Glide

class PicActivity : AppCompatActivity() {

    private val imageUrl: String = "https://sun9-20.userapi.com/impg/AbYoNm02YFsbSuGqovHK9hq_-hg_4RyxzpG4RA/nXBz7tq_d1Q.jpg?size=804x1080&quality=95&sign=a081c8fa206134ee43731d6da991fde2&type=album"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic)

        Glide.with(this)
            .load(imageUrl)
            .into(findViewById(R.id.picView))

    }
}