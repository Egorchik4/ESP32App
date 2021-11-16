package com.example.esp32app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.esp32app.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var myDataBase : DatabaseReference
    lateinit var userkey : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // ползунок
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                binding.textView.text = "$i"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                //Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
                //Toast.makeText(applicationContext,"stop tracking",Toast.LENGTH_SHORT).show()
            }
        })


        binding.bSTOP.setOnClickListener {
            binding.seekBar.progress = 0
            binding.textView.text = "0"
        }

        binding.switchLed.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                binding.textView.text = "Switch on"
                val r : Int
                r = 1
                val database = Firebase.database
                val myRef = database.getReference("data").child("led")
                myRef.setValue(r)

                // Change the app background color
                //binding.root.setBackgroundColor(Color.GREEN)
            } else {

                val r : Int
                r = 0
                val database = Firebase.database
                val myRef = database.getReference("data").child("led")
                myRef.setValue(r)
                // The switch is disabled
                binding.textView.text = "Switch off"
            }
        }
        binding.bSend.setOnClickListener {
            var r : String
            r = binding.textView.text.toString()
            val database = Firebase.database
            val myRef = database.getReference("data").child("number")

            myRef.setValue(r)

        }


    }



}