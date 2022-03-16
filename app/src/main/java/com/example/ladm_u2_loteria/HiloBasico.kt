package com.example.ladm_u2_loteria

import android.media.MediaPlayer
import android.util.Log
import android.widget.TextView
import kotlin.random.Random

class HiloBasico (act:MainActivity) : Thread() {

    var act=act
    var indexFaltante=0
    private var pausar =false
    private var ejecutar = true
    val arregloCartas = mutableListOf("El Diablito","La Dama","El catrin","El Paraguas","La Escalera","La Botella")
    var arregloImagenes = mutableListOf(R.drawable.carta1,R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,R.drawable.carta9,
                        R.drawable.carta10,R.drawable.carta11,R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,R.drawable.carta18,R.drawable.carta19,
                        R.drawable.carta20, R.drawable.carta21,R.drawable.carta22,R.drawable.carta23,R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,
                        R.drawable.carta30,R.drawable.carta31,R.drawable.carta32,R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,R.drawable.carta39,
                        R.drawable.carta40,R.drawable.carta41,R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,R.drawable.carta48,R.drawable.carta49,
                        R.drawable.carta50, R.drawable.carta51,R.drawable.carta52,R.drawable.carta53,R.drawable.carta54)

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

                        }
                            sleep(3000)
                    }
                } while (arregloCartas.size>0)
               // pausarHilo()
            }
        }

        fun mostrarFaltantesTexto(){
            act.runOnUiThread {
                var aux=arregloImagenes.size
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
