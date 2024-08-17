package com.example.habittracker.database.model

data class user_model(
    val id_usuario: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contraseña: String,
    val role: Int
){
    fun compareWith(userModel: user_model): Boolean{
        return this.id_usuario == userModel.id_usuario
    }
}