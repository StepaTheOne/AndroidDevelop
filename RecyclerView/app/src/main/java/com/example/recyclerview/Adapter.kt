package com.example.recyclerview

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Adapter(private val context: Context,
              private val list: ArrayList<ColorData>,
              private val cellClickListener: CellClickListener): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val colorQuad: View = itemView.findViewById(R.id.view)
        val colorName: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(this.context).inflate(R.layout.rview_item, parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.colorQuad.setBackgroundColor(list[position].colorHex)
        holder.colorName.text = list[position].colorName

        val data =list[position].colorName
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }

    }

    interface CellClickListener{
        fun onCellClickListener(data: String)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}