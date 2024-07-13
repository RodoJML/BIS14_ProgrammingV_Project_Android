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
import java.net.URLEncoder
import androidx.appcompat.app.AlertDialog


class BackgroundTask(private val context: Context) {

    private lateinit var alertDialog: AlertDialog

    fun execute(vararg params: String) {
        CoroutineScope(Dispatchers.Main).launch {
            onPreExecute()
            val result = doInBackground(*params)
            onPostExecute(result)
        }
    }

    private suspend fun doInBackground(vararg params: String): String? {

        return withContext(Dispatchers.IO) {
            val type = params[0]
            val userName = params[1]
            val password = params[2]
            val loginurl = "http://192.168.0.10/login.php"

            if (type == "login") {
                try {
                    val url = URL(loginurl)
                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    httpURLConnection.requestMethod = "POST"
                    httpURLConnection.doOutput = true
                    httpURLConnection.doInput = true

                    val postData =
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(
                            userName,
                            "UTF-8"
                        ) + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(
                            password,
                            "UTF-8"
                        )

                    httpURLConnection.outputStream.use { outputStream ->
                        outputStream.bufferedWriter(Charsets.UTF_8).use { writer ->
                            writer.write(postData)
                            writer.flush()
                        }
                    }

                    val result = StringBuilder()
                    httpURLConnection.inputStream.use { inputStream ->
                        inputStream.bufferedReader(Charsets.ISO_8859_1).use { reader ->
                            var line: String?
                            while (reader.readLine().also { line = it } != null) {
                                result.append(line)
                            }
                        }
                    }

                    httpURLConnection.disconnect()
                    return@withContext result.toString()

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                    return@withContext null

                } catch (e: IOException) {
                    e.printStackTrace()
                    return@withContext null
                }
            } else if(type == "getAll"){
                return@withContext null
            } else {
                return@withContext null
            }
        }
    }

    private fun onPreExecute() {
        alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle("Login Status")
    }

    private fun onPostExecute(result: String?) {

        alertDialog.setMessage(result ?: "Error occurred")
        alertDialog.show()
    }

    private fun onProgressUpdate() {
        // Code to update progress
    }
}