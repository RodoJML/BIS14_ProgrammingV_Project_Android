package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class HabitCreationActivity : AppCompatActivity() {

    private lateinit var edTitulo: EditText
    private lateinit var edFechaInicio: EditText
    private lateinit var edFechaFin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_creation)

        edTitulo = findViewById(R.id.edTitulo)
        edFechaInicio = findViewById(R.id.edFechaInicio)
        edFechaFin = findViewById(R.id.edFechaFin)

        val btnClick = findViewById<Button>(R.id.btnClick)
        btnClick.setOnClickListener {
            val titulo = edTitulo.text.toString().trim()
            val fechaInicio = edFechaInicio.text.toString().trim()
            val fechaFin = edFechaFin.text.toString().trim()

            if (titulo.isNotEmpty() && fechaInicio.isNotEmpty() && fechaFin.isNotEmpty()) {
                val intent = Intent(this, HabitDisplayActivity::class.java)
                intent.putExtra("EXTRA_TITULO", titulo)
                intent.putExtra("EXTRA_FECHA_INICIO", fechaInicio)
                intent.putExtra("EXTRA_FECHA_FIN", fechaFin)
                startActivity(intent)
            } else {
                // Manejar caso donde no se ingresaron todos los datos obligatorios
                // Puedes mostrar un mensaje de error o requerir que se completen todos los campos
            }
        }
    }
}

