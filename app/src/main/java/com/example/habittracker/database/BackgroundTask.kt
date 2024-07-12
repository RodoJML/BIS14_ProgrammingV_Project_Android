package com.example.habittracker.database
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class BackgroundTask(private val context: Context) {

    fun execute(vararg params: String) {
        CoroutineScope(Dispatchers.Main).launch {
            onPreExecute()
            val result = doInBackground(*params)
            onPostExecute(result)
        }
    }

    private suspend fun doInBackground(vararg params: String) {

        return withContext(Dispatchers.IO) {
            val type = params[0]
            val login_url = "http://10.0.2.2/login.php"

            if(type == "login"){
                try {
                    val url = URL(login_url)
                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    httpURLConnection.requestMethod = "POST"
                    httpURLConnection.doOutput = true
                    httpURLConnection.doInput = true



                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }


            }
        }
    }

    private fun onPreExecute() {
        // Code to execute before background task starts
    }

    private fun onPostExecute(result: String) {
        // Code to execute after background task finishes
    }

    private fun onProgressUpdate() {
        // Code to update progress
    }
}