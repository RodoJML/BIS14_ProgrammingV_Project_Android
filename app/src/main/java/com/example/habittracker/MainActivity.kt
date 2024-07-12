package com.example.habittracker

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.habittracker.database.BackgroundTask

class MainActivity : ComponentActivity() {
    private lateinit var UsernameEt: EditText
    private lateinit var PasswordEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logindb_layout)

        UsernameEt = findViewById<EditText>(R.id.dbuser_field)
        PasswordEt = findViewById<EditText>(R.id.dbpassword_field)


    }

    fun onLogin(view: View) {
        val userName = UsernameEt.text.toString()
        val password = PasswordEt.text.toString()
        val type = "login"
        val backgroundTask = BackgroundTask(this)
        backgroundTask.execute(type, userName, password)
    }
}


/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitTrackerTheme {
        Greeting("Android")
    }
}*/