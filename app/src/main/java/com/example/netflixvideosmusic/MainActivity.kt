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
                Media("Bottoms Trailer", "El trailer de bottoms", R.raw.bottoms),
                Media("CyberPunk Trailer", "El trailer de cyberpunk" , R.raw.cyberpunk),
                Media("Edgerunners Trailer", "El trailer de edgerunners", R.raw.edgerunners),
                Media("Ferrari Trailer", "El trailer de ferrari" , R.raw.ferrari),
                Media("Jesus Trailer", "El trailer de jesus" , R.raw.jesus),
                Media("Jhon Wick Trailer", "El trailer de jhon wick", R.raw.jhonwick),
                Media("Mortal Kombat Trailer", "El trailer de mortal kombat" , R.raw.mortalkombat),
                Media("Napoleon Trailer", "El trailer de napoleon", R.raw.napoleon),
                Media("One Piece Trailer", "El trailer de one piece" , R.raw.onepiece),
                Media("The Killers Trailer", "El trailer de the killers", R.raw.thekillers)
            )
        }else{
            mutableListOf(
                Media("Another Day", "Los recoditos", R.raw.anotherday),
                Media("Children of the sky", "Imagine Dragons" , R.raw.childrenofthesky),
                Media("Chlorine", "Twenty One Pilots", R.raw.chlorine),
                Media("Contenta", "Ed Maverick" , R.raw.contenta),
                Media("Holdin on to you", "Twenty One Pilots" , R.raw.holdinontotoyou),
                Media("La curiosidad", "Grupo chido", R.raw.lacuriosidad),
                Media("LowLife", "Jorgitos" , R.raw.lowlife),
                Media("Notion", "Perez Prado", R.raw.notion),
                Media("Que es eso", "No se" , R.raw.queeseso),
                Media("Something Real", "Post Malone", R.raw.somethingreal)
            )
        }

        //Se agrega la lista de canciones videos
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