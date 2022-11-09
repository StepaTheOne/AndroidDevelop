package com.example.recyclerview

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), Adapter.CellClickListener {

    private fun fillList():ArrayList<ColorData>{
        val data = this.resources.getStringArray(R.array.colors_name).toList()
        val data1 = this.resources.getIntArray(R.array.Color_HEX).toList()
        val endList = arrayListOf<ColorData>()
        var count = 0
        while(count<data.count()){
            endList.add(ColorData(data[count],data1[count]))
            count++
        }
        return endList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this,fillList(), this)
    }

    override fun onCellClickListener(data: String) {
        Toast.makeText(applicationContext,"This is $data",Toast.LENGTH_SHORT).show()
    }

}