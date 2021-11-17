package com.example.esp32app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.esp32app.R
import com.example.esp32app.databinding.ActivityMainBinding
import com.example.esp32app.screens.MenuFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashScreen)   // экран загрузки
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //показ MenuFragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MenuFragment())
            .commit()

    }



}