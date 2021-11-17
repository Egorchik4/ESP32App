package com.example.esp32app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.esp32app.R
import com.example.esp32app.databinding.ActivityMainBinding
import com.example.esp32app.screens.MenuFragment
import com.example.esp32app.screens.MenuFragmentViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashScreen)   // экран загрузки
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("aaa", "activity created")


        //показ MenuFragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MenuFragment())
            .commit()

    }



}