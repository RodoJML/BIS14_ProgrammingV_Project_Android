package com.example.habittracker.database.controller

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.habittracker.database.model.User_model
import com.example.habittracker.database.web.VolleySingleton
import com.google.gson.Gson
import org.json.JSONObject


class User_controller(private val context: Context) {

    private val url = "http://10.0.2.2:80/BIS14_user_controller.php"

    interface getAllCallback {
        fun onSuccess(userModels: List<User_model>)
        fun onError(errorMessage: String)
    }

    interface getByIdCallback {
        fun onSuccess(userModel: User_model)
        fun onError(errorMessage: String)
    }

    interface insertOneCallback {
        fun onSuccess()
        fun onError(errorMessage: String)
    }

    interface updateOneCallback {
        fun onSuccess()
        fun onError(errorMessage: String)
    }

    // Get All
    fun getAll(callback: getAllCallback) {
        VolleySingleton.getInstance(context).addToRequestQueue(
            JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    val state = response.getString("state")

                    when(state){
                        "1" -> {
                            val message = response.getJSONArray("users")
                            val userModels = Gson().fromJson(message.toString(), Array<User_model>::class.java).toList()
                            callback.onSuccess(userModels)
                        }
                        "2" -> {
                            Log.d("JSON Response (getAll)", "Database returns an error: ${response.getString("message")} ")
                            callback.onError(response.getString("message"))
                        }
                        else -> {
                            Log.d("JSON Response (getAll)", "Unable to read a valid state from JSON Object or returned a bad JSON Object")
                        }
                    }

                },
                { error ->
                    Log.d("REST GET Request", "Something wrong with the request: ${error.message}")
                }
            )
        )
    }

    // Get by ID
    fun getById(id: Int, callback: getByIdCallback) {
        VolleySingleton.getInstance(context).addToRequestQueue(
            JsonObjectRequest(
                Request.Method.GET,
                "$url?action=getById&key=$id",
                null,
                { response ->
                    val state = response.getString("state")

                    when(state){
                        "1" -> {
                            val message = response.getJSONObject("user")
                            val userModel = Gson().fromJson(message.toString(), User_model::class.java)
                            callback.onSuccess(userModel)
                        }
                        "2" -> {
                            Log.d("JSON Response (getById)", "Database returns an error: ${response.getString("message")} ")
                            callback.onError(response.getString("message"))
                        }
                        else -> {
                            Log.d("JSON Response (getById)", "Unable to read a valid state from JSON Object or returned a bad JSON Object")
                        }
                    }

                },
                { error ->
                    Log.d("REST GET Request", "Something wrong with the request: ${error.message}")
                }
            )
        )
    }

    // Insert One
    fun insertOne(userModel: User_model, callback: insertOneCallback) {

        val map = HashMap<String, String>()
        map.put("id_usuario", userModel.id_usuario.toString())
        map.put("nombre", userModel.nombre)
        map.put("apellido", userModel.apellido)
        map.put("correo", userModel.correo)
        map.put("contraseña", userModel.contraseña)
        map.put("role", userModel.role.toString())

        val jobject = (map as Map<*, *>?)?.let { JSONObject(it) }

        VolleySingleton.getInstance(context).addToRequestQueue(
            JsonObjectRequest(
                Request.Method.POST,
                url,
                jobject,
                { response ->
                    val state = response.getString("state")

                    when(state){
                        "1" -> {
                            callback.onSuccess()
                        }
                        "2" -> {
                            Log.d("JSON Response", "Database returns an error: ${response.getString("message")} ")
                            callback.onError(response.getString("message"))
                        }
                        else -> {
                            Log.d("JSON Response", "Unable to read a valid state from JSON Object or returned a bad JSON Object")
                        }
                    }

                },
                { error ->
                    Log.d("REST POST Request", "Something wrong with the request: ${error.message}")
                }
            )
        )
    }


    // Update One
    fun updateOne(userModel: User_model, callback: updateOneCallback) {

        val map = HashMap<String, String>()
        map.put("id_usuario", userModel.id_usuario.toString())
        map.put("nombre", userModel.nombre)
        map.put("apellido", userModel.apellido)
        map.put("correo", userModel.correo)
        map.put("contraseña", userModel.contraseña)
        map.put("role", userModel.role.toString())

        val jobject = (map as Map<*, *>?)?.let { JSONObject(it) }

        VolleySingleton.getInstance(context).addToRequestQueue(
            JsonObjectRequest(
                Request.Method.POST,
                "$url?action=update",
                jobject,
                { response ->
                    val state = response.getString("state")

                    when(state){
                        "1" -> {
                            callback.onSuccess()
                        }
                        "2" -> {
                            Log.d("JSON Response", "Database returns an error: ${response.getString("message")} ")
                            callback.onError(response.getString("message"))
                        }
                        else -> {
                            Log.d("JSON Response", "Unable to read a valid state from JSON Object or returned a bad JSON Object")
                        }
                    }

                },
                { error ->
                    Log.d("REST PUT Request", "Something wrong with the request: ${error.message}")
                }
            )
        )
    }



}