package com.example.ladm_u2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding
import kotlin.random.Random
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {
    var mp:MediaPlayer?=null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       var hilo1=HiloBasico(this)

        binding.btnIniciar.setOnClickListener {
            hilo1.start()
            binding.btnIniciar.isEnabled=false
        }

        binding.btnDetener.setOnClickListener {
            if(hilo1.estaPausado())
                hilo1.despausarHilo()
            else
                hilo1.pausarHilo()

        }

        binding.btnMostrar.setOnClickListener {
            hilo1.mostrar()
        }

    }
}