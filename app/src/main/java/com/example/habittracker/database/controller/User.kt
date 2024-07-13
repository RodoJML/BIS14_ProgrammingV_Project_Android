package com.example.habittracker.database.controller
import com.example.habittracker.database.model.User

class User(url: String) {
    var model: User = User(url)


    fun action(action: String, data: String): String?{

        when(action) {
            "getAll" -> {
                model.getAll()
            }
            "register" -> {
                // Code for register action
            }
            "login" -> {
                // Code for register action
            }
            else -> {
                // Code for default case
            }
        }
    }



}