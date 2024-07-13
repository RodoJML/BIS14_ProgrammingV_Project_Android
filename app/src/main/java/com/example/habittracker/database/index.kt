package com.example.habittracker.database

import com.example.habittracker.database.controller.User


class index() {

    val hostname = "http://192.168.0.10"

    fun database(table: String, action: String, data: String){

        when (table) {
            "usuario" -> {
                val user = User("$hostname/user")
                user.action(action, data)
            }
            "habito" -> {
                // Code for Habit table
            }
            "HabitLog" -> {
                // Code for HabitLog table
            }
            else -> {
                // Code for default case
            }
        }
    }

}
