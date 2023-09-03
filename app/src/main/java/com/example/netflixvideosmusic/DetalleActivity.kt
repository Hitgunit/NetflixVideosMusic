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

    private lateinit var txtTitle: TextView
    private lateinit var txtDetail: TextView
    private lateinit var btnBack: Button
    private lateinit var videoView: VideoView
    private lateinit var btnSaltar: Button

    private var anuncioPlaying = false

    private val tiempoSaltar = 10000L

    //Guarda la posicion del video
    private var videoPosition: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        txtTitle = findViewById(R.id.txtTittle)
        txtDetail = findViewById(R.id.txtDetalle)
        btnBack = findViewById(R.id.btnBack)
        videoView = findViewById(R.id.videoView)
        btnSaltar = findViewById(R.id.btnSaltar)

        //Se oculta el boton
        btnSaltar.visibility = View.INVISIBLE

        btnBack.setOnClickListener {
            onBackPressed()
        }

        Informacion()
    }

    private fun Random(min: Long, max: Long): Long {
        return Random.nextLong(min, max)
    }

    private fun Informacion() {
        val data = intent.getParcelableExtra<Media>("media") ?: return

        txtTitle.text = data.name
        txtDetail.text = data.description

        val uri = Uri.parse("android.resource://$packageName/${data.url}")
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        videoView.setOnPreparedListener {
            if (!anuncioPlaying) {
                anuncioPlaying = true
                GlobalScope.launch(Dispatchers.Main) {
                    val tiempo = Random(2000L, 30000L)
                    delay(tiempo)
                    //Guarda la posicion del video
                    videoPosition = videoView.currentPosition
                    videoView.pause()
                    //Agrega el video de anuncio
                    val adUri = Uri.parse("android.resource://$packageName/${R.raw.comercial}")
                    videoView.setVideoURI(adUri)
                    videoView.start()
                    //Tarda los 10s para iniciar el boton
                    delay(tiempoSaltar)
                    //Manda llama rle boton
                    SaltarAnuncio()
                }
            }
        }
        //Vuelve al video original si no se presiona el boton
        videoView.setOnCompletionListener {
            if (anuncioPlaying) {
                val originalUri = Uri.parse("android.resource://$packageName/${data.url}")
                videoView.setVideoURI(originalUri)
                //Reaunuda el video en el segundo que se pauso
                videoView.setOnPreparedListener { mp ->
                    mp.seekTo(videoPosition)
                    videoView.start()
                }
                btnSaltar.visibility = View.INVISIBLE
                anuncioPlaying = false
            }
        }
    }

    private fun SaltarAnuncio() {
        //Se muestra y se deshabilita el boton
        btnSaltar.visibility = View.VISIBLE
        btnSaltar.isEnabled = false

        //Inicia otra corrutina
        GlobalScope.launch(Dispatchers.Main) {
            //Cuenta regresiva para saltar el anuncio
            for (i in 5 downTo 0) {
                delay(1000)
                btnSaltar.text = "Saltar en $i s"
            }
            btnSaltar.text = "Saltar Ahora"
            btnSaltar.isEnabled = true

            btnSaltar.setOnClickListener {
                //Se obtine ela informaicon del intent para volver al video original
                val data = intent.getParcelableExtra<Media>("media") ?: return@setOnClickListener
                val originalUri = Uri.parse("android.resource://$packageName/${data.url}")
                videoView.setVideoURI(originalUri)
                // Reanuda el vídeo en la posición guardada
                videoView.seekTo(videoPosition)
                videoView.start()
                btnSaltar.visibility = View.INVISIBLE
                anuncioPlaying = false
            }
        }
    }
}
