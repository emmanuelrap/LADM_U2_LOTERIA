package com.example.ladm_u2_loteria

import android.widget.TextView

class HiloBasico (et: TextView) : Thread() {
        private var pausar =false
        private var ejecutar = true
        var index =0

        override fun run() {
            super.run()

            while(ejecutar){
                if(!pausar){
                    //se pone lo que quiero que ejecute

                }
                sleep(500)
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
