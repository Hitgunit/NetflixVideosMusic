package com.example.netflixvideosmusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mediaList: MutableList<Media>
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

    }

    fun StartRecycler(tipo:String){
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        mediaList = if (tipo == "video"){
            mutableListOf(
                Media("Bottoms", "El trailer de bottoms", R.raw.bottoms),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.cyberpunk),
                Media("Video", "Hola soy una descripcion", R.raw.edgerunners),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.ferrari),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.jesus),
                Media("Video", "Hola soy una descripcion", R.raw.jhonwick),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.mortalkombat),
                Media("Video", "Hola soy una descripcion", R.raw.napoleon),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.onepiece),
                Media("Video", "Hola soy una descripcion", R.raw.thekillers)
            )
        }else{
            mutableListOf(
                Media("Musica", "El trailer de bottoms", R.raw.anotherday),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.childrenofthesky),
                Media("Video", "Hola soy una descripcion", R.raw.chlorine),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.contenta),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.holdinontotoyou),
                Media("Video", "Hola soy una descripcion", R.raw.lacuriosidad),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.lowlife),
                Media("Video", "Hola soy una descripcion", R.raw.notion),
                Media("Video 2", "Hola soy una descripcion2" , R.raw.queeseso),
                Media("Video", "Hola soy una descripcion", R.raw.somethingreal)
            )
        }


        val adapter = CustomAdapter(mediaList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //Se le indica que hacer cuando recibe un clik dentro el mismo card
        adapter.onItemClick={
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("media", it)
            startActivity(intent)
        }
    }
}