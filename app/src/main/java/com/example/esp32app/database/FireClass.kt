package com.example.esp32app.database

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FireClass{

    fun senddata(path: String, child: String, value: Int){

        val database = Firebase.database
        val myRef = database.getReference(path).child(child)
        myRef.setValue(value)

    }
}