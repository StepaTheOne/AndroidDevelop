package com.example.gson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class ImageAdapter(private val context: Context,
                   private val list: ArrayList<String>,
                   private val Listener: CellClickListener): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(this.context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        Glide.with(context).load(data).into(holder.image)

        holder.itemView.setOnClickListener{
            Listener.onCellClickListener(position)
        }
    }

    interface CellClickListener {
        fun onCellClickListener(position: Int)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}