package com.example.ladm_u2_loteria

import android.widget.TextView
import kotlin.random.Random

class HiloBasico (act:MainActivity) : Thread() {
    var act=act
    var cartasQuedan=10;
    val numbers = mutableListOf("carta1","carta2","carta3","carta4","carta5","carta6","carta7","carta8","carta9","carta10")

    private var pausar =false
    private var ejecutar = true

        override fun run() {
            super.run()
             while(ejecutar){

                do {
                    if(cartasQuedan>0){
                        act.runOnUiThread {
                            var indexRemover = Random.nextInt(cartasQuedan-1)
                            act.binding.tv.text = act.binding.tv.text.toString() + "\n" + numbers[indexRemover]
                            act.binding.tv2.text =act.binding.tv2.text.toString() + "\n" + indexRemover.toString()
                            numbers.removeAt(indexRemover)
                           }
                            cartasQuedan--
                            sleep(1000)
                    }
                } while (cartasQuedan>0)
                 sleep(100000)
                pausarHilo()
            }
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
