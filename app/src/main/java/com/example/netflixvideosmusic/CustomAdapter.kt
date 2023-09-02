package com.example.netflixvideosmusic

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var media: List<Media>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var onItemClick: ((Media) -> Unit)?= null

    //Llama lo que se desea poblar
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.itemTitle)
        var itemDetalle: TextView = itemView.findViewById(R.id.itemDetalle)
    }

    //Es lo que crea cuando se muetsra la app por porimera vez
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
        //Se pasan los valores acorde al tiem correspondiente
        val media = media[position]
        holder.itemTitle.text = media.name
        holder.itemDetalle.text = media.description

        //Se iniciliza el clickListener
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(media)
        }
    }
}


