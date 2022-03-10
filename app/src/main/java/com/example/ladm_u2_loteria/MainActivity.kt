package com.example.ladm_u2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var cartasQuedan=10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var tv = findViewById<TextView>(R.id.tv)

        val numbers = mutableListOf("1","2","3","4","5","6","7","8","9","0")

        do {
            var indexRemover = Random.nextInt(cartasQuedan)
            /*
            if (numbers.removeAt(indexRemover)) {

                tv.text=tv.text.toString()+"\n$indexRemover eliminado"
            } else {
                tv.text=tv.text.toString()+"\n Coleccion Vacia"
            }*/

            if(indexRemover<numbers.size){
                tv.text=tv.text.toString()+"\n"+numbers[indexRemover]
                numbers.removeAt(indexRemover)
                cartasQuedan--
            }
        } while (numbers.size>0)

    }
}