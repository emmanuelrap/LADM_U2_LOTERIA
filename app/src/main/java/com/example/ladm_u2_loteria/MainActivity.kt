package com.example.ladm_u2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding
import kotlin.random.Random
import android.media.MediaPlayer
import androidx.core.view.isVisible
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var act = this
    var mp:MediaPlayer?=null
    var mpCancion:MediaPlayer?=null
    lateinit var binding: ActivityMainBinding
    var escucharMusica=true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var hiloDarCartas=HiloBasico(this)


       val corutinaMusica= GlobalScope.launch {
           mpCancion= MediaPlayer.create(act,R.raw.cancionloteria)
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
                    mpCancion= MediaPlayer.create(act,R.raw.cancionloteria)
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
             binding.tv2.isVisible=false

         }




        binding.btnIniciar.setOnClickListener {
            hiloDarCartas.start()
            binding.btnIniciar.isEnabled=false
        }

        binding.btnDetener.setOnClickListener {
            if(hiloDarCartas.estaPausado())
                hiloDarCartas.despausarHilo()
            else
                hiloDarCartas.pausarHilo()

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

            if(hiloDarCartas.arregloCartas.size==0){}
                else{
                    binding.tvFaltantes.text=""
                    hiloDarCartas.mostrarFaltantesTexto()
                    binding.btnSig.isEnabled=true
                }
        }

        binding.btnSig.setOnClickListener {
            if(hiloDarCartas.arregloCartas.size==0){}
            else{
                hiloDarCartas.mostrarFaltantesImagenes()
        }
        }



    }
}