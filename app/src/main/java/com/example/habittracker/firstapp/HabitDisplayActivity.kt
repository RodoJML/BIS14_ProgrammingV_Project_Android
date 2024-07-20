package com.example.habittracker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HabitDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_display)

        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val titulo = intent.getStringExtra("EXTRA_TITULO") ?: ""
        val fechaInicio = intent.getStringExtra("EXTRA_FECHA_INICIO") ?: ""
        val fechaFin = intent.getStringExtra("EXTRA_FECHA_FIN") ?: ""

        // Mostrar los datos en el TextView
        tvResultado.text = "Título: $titulo\nFecha de inicio: $fechaInicio\nFecha de fin: $fechaFin"
    }
}
