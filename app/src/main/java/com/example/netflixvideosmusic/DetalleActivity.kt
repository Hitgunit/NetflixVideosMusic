package com.example.netflixvideosmusic

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

class DetalleActivity : AppCompatActivity() {

    lateinit var txtTittle: TextView
    lateinit var txtDetalle: TextView
    lateinit var btnBack: Button
    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        txtTittle = findViewById(R.id.txtTittle)
        txtDetalle = findViewById(R.id.txtDetalle)
        btnBack = findViewById(R.id.btnBack)
        videoView = findViewById(R.id.videoView)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        //Se inicia el fun para obtener la informacion
        Informacion()
    }

    fun Informacion(){
        //Se obtiene el obejto con los datos recibidos
        val data = intent.getParcelableExtra<Media>("media")
        if (data != null){
            //Se asigna los valores name y description
            txtTittle.text = data.name
            txtDetalle.text = data.description



            //Se añade el URL de uri
            val uri = Uri.parse("android.resource://$packageName/${data.url}")

            //Se asgina el mediacontroller
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)

            //Se le asigna el Uri deseado
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

        }
    }
}