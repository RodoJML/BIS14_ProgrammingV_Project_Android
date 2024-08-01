package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivityMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btnCrearHabito = findViewById<Button>(R.id.btnCrearHabito)
        btnCrearHabito.setOnClickListener {
            navigateToCreateHabitActivity()
        }
    }

    private fun navigateToCreateHabitActivity() {
        val intent = Intent(this, CreateHabitActivity::class.java)
        startActivity(intent)
    }
}

