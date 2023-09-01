package com.example.netflixvideosmusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        var btnVideo: Button = findViewById(R.id.btnVideo)
        var btnMusica: Button = findViewById(R.id.btnMusic)

        btnVideo.setOnClickListener {
            StartRecycler("video")
        }

        btnMusica.setOnClickListener {
            StartRecycler("musica")
        }

        var mediaList = mutableListOf(
            Media("Adair", "Hola soy una descripcion", R.drawable.ic_launcher_foreground)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(mediaList)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    fun StartRecycler(tipo:String){
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        if (tipo == "video"){
            
        }else{

        }
    }
}