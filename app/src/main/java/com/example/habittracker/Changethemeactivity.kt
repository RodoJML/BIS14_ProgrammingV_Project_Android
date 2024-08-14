//package com.example.testapp
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatDelegate
//import com.google.android.material.switchmaterial.SwitchMaterial
//
//class ChangeThemeActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.content_main)
//
//        // Referencia correcta al ID
//        val swDarkMode = findViewById<SwitchMaterial>(R.id.swDarkMode)
//
//        swDarkMode.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                enableDarkMode()
//            } else {
//                disableDarkMode()
//            }
//        }
//    }
//
//    private fun enableDarkMode() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        delegate.applyDayNight()
//    }
//
//    private fun disableDarkMode() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        delegate.applyDayNight()
//    }
//}
