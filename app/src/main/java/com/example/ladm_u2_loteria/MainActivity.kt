package com.example.ladm_u2_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var tv = findViewById<TextView>(R.id.tv)

        val numbers = mutableListOf(5, 1, 2, 3, 4, 6, 9, 7, 10)

        do {
            val numberToRemove = numbers.randomOrNull()
            if (numbers.remove(numberToRemove)) {

                tv.text=tv.text.toString()+"\n$numberToRemove eliminado"
            } else {
                tv.text=tv.text.toString()+"\n Coleccion Vacia"
            }

        } while (numberToRemove != null)

    }
}