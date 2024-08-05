package com.example.habittracker.database.controller

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.habittracker.database.web.VolleySingleton
import com.google.gson.Gson
import com.example.habittracker.database.model.User_model

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
                            val message = response.getJSONArray("user")
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
}