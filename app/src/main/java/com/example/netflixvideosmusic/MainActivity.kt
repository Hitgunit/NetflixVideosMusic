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
                Media("Video 2", "Hola soy una descripcion2" , R.drawable.ic_launcher_background),
                Media("Video", "Hola soy una descripcion", R.drawable.ic_launcher_foreground),
                Media("Video 2", "Hola soy una descripcion2" , R.drawable.ic_launcher_background)


            )
        }else{
            mutableListOf(
                Media("Another Day", "Esta es la cancion de another day", R.raw.anotherday)
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