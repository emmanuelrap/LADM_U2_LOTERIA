package com.example.ladm_u2_loteria

import android.media.MediaPlayer
import android.util.Log
import android.widget.TextView
import kotlin.random.Random

class HiloBasico (act:MainActivity) : Thread() {
    var cartasQuedan=6
    var act=act
    var  aux=1
    var indexFaltante=0
    private var pausar =false
    private var ejecutar = true
    val arregloCartas = mutableListOf("El Diablito","La Dama","El catrin","El Paraguas","La Escalera","La Botella")
    var arregloImagenes = mutableListOf(R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta7,R.drawable.carta8)
    var arregloSonidos = mutableListOf(R.raw.carta2,R.raw.carta3,R.raw.carta4,R.raw.carta5,R.raw.carta7,R.raw.carta8)


        override fun run() {

            super.run()
            var arregloImagenesCopia=arregloImagenes
             while(ejecutar){
                do {
                    while(pausar){
                        sleep(500)
                    }
                    if(arregloCartas.size>0 ){
                        act.runOnUiThread {
                            //Random
                            var indexRemover = Random.nextInt(arregloCartas.size)
                            //Sonido
                            act.mp= MediaPlayer.create(act,arregloSonidos[indexRemover])
                            act.mp?.start()
                            //Imagenes
                            act.binding.imagen.setImageResource(arregloImagenesCopia[indexRemover])
                            act.binding.tv.text =  arregloCartas[indexRemover]
                            //Removicion de los que salieron
                            arregloCartas.removeAt(indexRemover)
                            arregloImagenesCopia.removeAt(indexRemover)
                            arregloSonidos.removeAt(indexRemover)
                            cartasQuedan--
                        }
                            sleep(3000)
                    }
                } while (arregloCartas.size>0)
               // pausarHilo()
            }
        }

        fun mostrarFaltantesTexto(){
            act.runOnUiThread {
                var aux=cartasQuedan
                while (aux>0){
                    act.binding.tvFaltantes.text = act.binding.tvFaltantes.text.toString()+ "\n"+arregloCartas[aux-1]
                    aux--
                }

                //mostrar imagen faltantes
                indexFaltante=arregloImagenes.size-1
                mostrarFaltantesImagenes()

            }
        }

    fun mostrarFaltantesImagenes(){
        if(indexFaltante==0)
            indexFaltante= arregloImagenes.size-1

        act.binding.imagenFaltante1.setImageResource(arregloImagenes[indexFaltante])
        indexFaltante--

        if(indexFaltante==0)
            indexFaltante= arregloImagenes.size-1

        act.binding.imagenFaltante2.setImageResource(arregloImagenes[indexFaltante])
        indexFaltante--

        if(indexFaltante==0)
            indexFaltante= arregloImagenes.size-1

        act.binding.imagenFaltante3.setImageResource(arregloImagenes[indexFaltante])
        indexFaltante--

        if(indexFaltante==0)
            indexFaltante= arregloImagenes.size-1

        act.binding.imagenFaltante4.setImageResource(arregloImagenes[indexFaltante])
        indexFaltante--

        if(indexFaltante==0)
            indexFaltante= arregloImagenes.size-1

        act.binding.imagenFaltante5.setImageResource(arregloImagenes[indexFaltante])
        indexFaltante--
    }

        fun terminarHilo(){
            ejecutar=false
        }

        fun pausarHilo(){
            pausar = true
        }

        fun despausarHilo(){
            pausar=false
        }

        fun estaPausado(): Boolean{
            return pausar
        }


    }
