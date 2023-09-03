package com.example.netflixvideosmusic

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class DetalleActivity : AppCompatActivity() {

    lateinit var txtTittle: TextView
    lateinit var txtDetalle: TextView
    lateinit var btnBack: Button
    lateinit var videoView: VideoView
    lateinit var btnSaltar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        txtTittle = findViewById(R.id.txtTittle)
        txtDetalle = findViewById(R.id.txtDetalle)
        btnBack = findViewById(R.id.btnBack)
        videoView = findViewById(R.id.videoView)
        btnSaltar = findViewById(R.id.btnSaltar)
        btnSaltar.visibility = View.INVISIBLE


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

            videoView.setOnPreparedListener{
                    GlobalScope.launch(Dispatchers.Main){
                        //Tiempo aleatorio en milisegundos
                        delay(Random.nextLong(5000, videoView.duration.toLong()))
                        //Se pausa el video
                        videoView.pause()
                        //Se agrega el URL del comercial
                        val adUri = Uri.parse("android.resource://$packageName/${R.raw.comercial}")
                        videoView.setVideoURI(adUri)
                        videoView.start()
                        //Se espera los 10 segundos para mostrar el boton
                        delay(10000)


                }
            }


        }
    }


}