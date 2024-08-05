package com.example.habittracker.database.web

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Singleton class representing a Volley HTTP client
 */
class VolleySingleton private constructor(context: Context) {

    // Attributes
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        @Volatile
        private var INSTANCE: VolleySingleton? = null

        /**
         * Returns the unique instance of the singleton
         * @param context Context where the requests will be executed
         * @return Singleton instance
         */
        fun getInstance(context: Context): VolleySingleton {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleySingleton(context).also { INSTANCE = it }
            }
        }
    }

    /**
     * Adds a request to the queue
     * @param req Request to add
     * @param T Type of the result
     */
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
