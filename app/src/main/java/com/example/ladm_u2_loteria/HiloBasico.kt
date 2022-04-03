package com.example.ladm_u2_loteria

import android.media.MediaPlayer
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlin.random.Random

class HiloBasico (act:MainActivity) : Thread() {

    var act=act
    var turno=0
    var indexFaltante=0
    private var pausar =false
    private var ejecutar = true
    var arregloCartasSalieron = mutableListOf(R.drawable.carta1)
    var arregloCartas = mutableListOf("El Gallo","El Diablito","La Dama","El catrin","El Paraguas","La sirena","La Escalera","La Botella","El Barril", "El arbol","El melon","El valiente","El gorrito","La muerte","La pera", "La bandera","El bandolon",
                                        "El violoncello","La garza","El pajaro","La mano","La Bota","La luna","El cotorro","El borracho","El negrito","El corazon","La sandia","El tambor","El camaron","Las jaraz","El musico","La araña","El Soldado",
                                        "La estrella","El Cazo","El mundo","El apache","El nopal","El Alacran","La Rosa","La Calavera","La Campana","El Cantarito","El Venado","El sol","La corona","La chalupa","El pino","El pescado","La palma","La maceta","El Arpa","La rana")

    var arregloImagenes = mutableListOf(R.drawable.carta1,R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,R.drawable.carta9,
                        R.drawable.carta10,R.drawable.carta11,R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,R.drawable.carta18,R.drawable.carta19,
                        R.drawable.carta20, R.drawable.carta21,R.drawable.carta22,R.drawable.carta23,R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,
                        R.drawable.carta30,R.drawable.carta31,R.drawable.carta32,R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,R.drawable.carta39,
                        R.drawable.carta40,R.drawable.carta41,R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,R.drawable.carta48,R.drawable.carta49,
                        R.drawable.carta50, R.drawable.carta51,R.drawable.carta52,R.drawable.carta53,R.drawable.carta54)

    var arregloSonidos = mutableListOf(R.raw.carta1,R.raw.carta2,R.raw.carta3,R.raw.carta4,R.raw.carta5,R.raw.carta6,R.raw.carta7,R.raw.carta8,R.raw.carta9,
                        R.raw.carta10,R.raw.carta11,R.raw.carta12,R.raw.carta13,R.raw.carta14,R.raw.carta15,R.raw.carta16,R.raw.carta17,R.raw.carta18,R.raw.carta19,
                        R.raw.carta20, R.raw.carta21,R.raw.carta22,R.raw.carta23,R.raw.carta24,R.raw.carta25,R.raw.carta26,R.raw.carta27,R.raw.carta28,R.raw.carta29,
                        R.raw.carta30,R.raw.carta31,R.raw.carta32,R.raw.carta33,R.raw.carta34,R.raw.carta35,R.raw.carta36,R.raw.carta37,R.raw.carta38,R.raw.carta39,
                        R.raw.carta40,R.raw.carta41,R.raw.carta42,R.raw.carta43,R.raw.carta44,R.raw.carta45,R.raw.carta46,R.raw.carta47,R.raw.carta48,R.raw.carta49,
                        R.raw.carta50, R.raw.carta51,R.raw.carta52,R.raw.carta53,R.raw.carta54)

        override fun run() {
            super.run()
            var arregloImagenesCopia=arregloImagenes
            while (turno<52 && ejecutar){
                turno++
                    while(pausar){
                        sleep(500)
                    }
                        act.runOnUiThread {
                            //Random
                            var indexRemover = Random.nextInt(arregloCartas.size)
                            //Sonido
                            act.mp= MediaPlayer.create(act,arregloSonidos[indexRemover])
                            act.mp?.start()
                            //Imagenes
                            act.binding.imagen.setImageResource(arregloImagenesCopia[indexRemover])
                            act.binding.tv.text =  arregloCartas[indexRemover]
                            arregloCartasSalieron.add(arregloImagenes[indexRemover])

                            //imagenes anteriores
                            if(arregloImagenes.size==54){
                                act.binding.imagenAnt1.setImageResource(arregloCartasSalieron[turno])
                            }else
                            if(arregloImagenes.size==53){
                                act.binding.imagenAnt1.setImageResource(arregloCartasSalieron[turno])
                                act.binding.imagenAnt2.setImageResource(arregloCartasSalieron[turno-1])
                            }else
                              {
                                 act.binding.imagenAnt1.setImageResource(arregloCartasSalieron[turno])
                                 act.binding.imagenAnt2.setImageResource(arregloCartasSalieron[turno-1])
                                 act.binding.imagenAnt3.setImageResource(arregloCartasSalieron[turno-2])
                             }
                              //Removicion de los que salieron
                               arregloCartas.removeAt(indexRemover)
                               arregloImagenesCopia.removeAt(indexRemover)
                               arregloSonidos.removeAt(indexRemover)

                        }
                        //Simulacion Ruleta

                            sleep(3000)
                            var aux = Random.nextInt(9)+2
                            var decrementoTiempo = 100L
                            while (aux != 0) {
                                act.runOnUiThread {
                                    var aux2 = Random.nextInt(arregloImagenes.size - 1)
                                    act.binding.tv.text = arregloCartas[aux2]
                                    act.binding.imagen.setImageResource(arregloImagenes[aux2])
                                    aux--
                                    act.mp = MediaPlayer.create(act, R.raw.sonidouno)
                                    act.mp?.start()
                                }
                                decrementoTiempo +=100

                                sleep(decrementoTiempo)
                            }

                            act.mp?.release()
                            sleep(200L)
                            act.mp = MediaPlayer.create(act, R.raw.sonidodos)
                            act.mp?.start()



                            //poner cartas faltantes
                            if (arregloCartas.size == 0)
                            else {
                                act.runOnUiThread {
                                    act.binding.tvFaltantes.text = ""
                                    mostrarFaltantesTexto()
                                    act.binding.btnSig.isEnabled = true
                                }
                          }
                        }
                          act.runOnUiThread { Toast.makeText(act,"JUEGO TERMINADO", Toast.LENGTH_SHORT).show()}
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



