package com.example.gson

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val context: Context,
                   private val list: MutableList<Bitmap>,

): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
        //val image2: ImageView = itemView.findViewById(R.id.image2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(this.context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageBitmap(list[position])

        val data = list[position]



    }

    interface CellClickListener {
        fun onCellClickListener(data: Bitmap)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}