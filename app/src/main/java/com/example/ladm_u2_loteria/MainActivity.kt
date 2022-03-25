package com.example.ladm_u2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding
import kotlin.random.Random
import android.media.MediaPlayer
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    var act = this
    var reiniciado=false
    var mp:MediaPlayer?=null
    var mpCancion:MediaPlayer?=null
    lateinit var binding: ActivityMainBinding
    var escucharMusica=true
    var mostrarVideo=true

    var arregloImagenes = mutableListOf(R.drawable.carta1,R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,R.drawable.carta9,
                        R.drawable.carta10,R.drawable.carta11,R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,R.drawable.carta18,R.drawable.carta19,
                        R.drawable.carta20, R.drawable.carta21,R.drawable.carta22,R.drawable.carta23,R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,
                        R.drawable.carta30,R.drawable.carta31,R.drawable.carta32,R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,R.drawable.carta39,
                        R.drawable.carta40,R.drawable.carta41,R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,R.drawable.carta48,R.drawable.carta49,
                        R.drawable.carta50, R.drawable.carta51,R.drawable.carta52,R.drawable.carta53,R.drawable.carta54)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var hiloDarCartas=HiloBasico(this)


       val corutinaMusica= GlobalScope.launch {
           mpCancion= MediaPlayer.create(act,R.raw.musicaneed)
           mpCancion?.start()

        }

        val corutinaDetenerMusica= GlobalScope.launch {
            binding.btnMusica.setOnClickListener {

                if(escucharMusica){
                    escucharMusica=false
                    mpCancion?.stop()
                    binding.btnMusica.setBackgroundResource(R.drawable.mute)
                }else{
                    escucharMusica=true
                    mpCancion= MediaPlayer.create(act,R.raw.musicaneed)
                    mpCancion?.start()
                    binding.btnMusica.setBackgroundResource(R.drawable.iconomusica)
                }
            }

        }

         val corutinaOcultarCosas= GlobalScope.launch {
             binding.tvFaltantes.isVisible = false
             binding.btnSig.isVisible=false
             binding.imagenFaltante1.isVisible=false
             binding.imagenFaltante2.isVisible=false
             binding.imagenFaltante3.isVisible=false
             binding.imagenFaltante4.isVisible=false
             binding.imagenFaltante5.isVisible=false
             binding.imagenAnt1.isVisible=false
             binding.imagenAnt2.isVisible=false
             binding.imagenAnt3.isVisible=false
             binding.tv2.isVisible=false
             binding.btnDetener.isVisible=false
             binding.btnMostrar.isVisible=false


         }

        val mostrarVideoBorracho= GlobalScope.launch {
            binding.videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.videoborracho)
            while(true){
                binding.videoView.start()
                delay(4000)
            }
        }

        fun prepararPantalla() {
            binding.btnIniciar.isVisible = false
            binding.btnDetener.isVisible = true
            binding.btnMostrar.isVisible = true
            binding.tv.isVisible = true
            binding.videoView.isVisible = false

            binding.imagenAnt1.isVisible = true
            binding.imagenAnt2.isVisible = true
            binding.imagenAnt3.isVisible = true

            binding.imagen.isVisible = true
        }

            binding.btnIniciar.setOnClickListener {
                hiloDarCartas.start()
                prepararPantalla()

            }


            binding.btnDetener.setOnClickListener {

                if(hiloDarCartas.estaPausado()){
                    Toast.makeText(this,"REANUDAR",Toast.LENGTH_LONG).show()
                    hiloDarCartas.despausarHilo()
                }
                else {
                    Toast.makeText(this,"PAUSADO",Toast.LENGTH_LONG).show()
                    hiloDarCartas.pausarHilo()
                }

            }

            binding.btnMostrar.setOnClickListener {
                binding.tvFaltantes.isVisible = true
                binding.btnSig.isVisible=true
                binding.imagenFaltante1.isVisible=true
                binding.imagenFaltante2.isVisible=true
                binding.imagenFaltante3.isVisible=true
                binding.imagenFaltante4.isVisible=true
                binding.imagenFaltante5.isVisible=true
                binding.tv2.isVisible=true

            }

            binding.btnSig.setOnClickListener {
                if(hiloDarCartas.arregloCartas.size==0){}
                else{
                    hiloDarCartas.mostrarFaltantesImagenes()
                }
            }


        }

    }
