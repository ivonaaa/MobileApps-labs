package com.example.orwma_lv5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unesibutton = findViewById<Button>(R.id.UnesiButton)
        unesibutton.setOnClickListener {
            val ime = findViewById<EditText>(R.id.ImeEditText)
            val opis = findViewById<EditText>(R.id.OpisEditText)
            val imeText = findViewById<TextView>(R.id.textView)
            val opisText = findViewById<TextView>(R.id.textView2)
            imeText.text = ime.text
            opisText.text = opis.text

        }
        val izracunajbutton = findViewById<Button>(R.id.izracunajButton)
        izracunajbutton.setOnClickListener {
            val tezina = findViewById<EditText>(R.id.weightEditTextText)
            val visina = findViewById<EditText>(R.id.hightEditText)
            val w = tezina.text.toString().toDouble()
            val h = visina.text.toString().toDouble()/100
            val bmi = w/(h*h)
            val string : String=bmi.toString()
            Toast.makeText(this, string , Toast.LENGTH_LONG).show()
        }

    }
}