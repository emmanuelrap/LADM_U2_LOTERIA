package com.example.ladm_u2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding
import kotlin.random.Random
import android.media.MediaPlayer
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    var mp:MediaPlayer?=null
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSig.isEnabled=false
        binding.tv2.isVisible=false



       var hilo1=HiloBasico(this)

        binding.btnIniciar.setOnClickListener {
            hilo1.start()
            binding.btnIniciar.isEnabled=false
            binding.tv2.isVisible=true
        }

        binding.btnDetener.setOnClickListener {
            if(hilo1.estaPausado())
                hilo1.despausarHilo()
            else
                hilo1.pausarHilo()

        }

        binding.btnMostrar.setOnClickListener {
            if(hilo1.arregloCartas.size==0){}
                else{
                    binding.tvFaltantes.text=""
                    hilo1.mostrarFaltantesTexto()
                    binding.btnSig.isEnabled=true
                }


        }

        binding.btnSig.setOnClickListener {
            if(hilo1.arregloCartas.size==0){}
            else{
                hilo1.mostrarFaltantesImagenes()
        }

        }



    }
}