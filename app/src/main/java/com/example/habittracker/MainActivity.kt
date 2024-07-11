package com.example.habittracker

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.habittracker.ui.theme.HabitTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userNameEt = findViewById<EditText>(R.id.dbuser_field)
        val passwordEt = findViewById<EditText>(R.id.dbpassword_field)




        /*enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(


                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/

    }
}

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
}