package com.example.netflixvideosmusic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var media: List<Media>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        var itemDetalle: TextView = itemView.findViewById(R.id.itemDetalle)
    }

    //Cuando crea la variables por primera vez
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    //Tamaño del arreglo
    override fun getItemCount(): Int {
        return media.size
    }

    //Poblar cada elemento
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = media[position].name
        holder.itemDetalle.text = media[position].name
    }
}


