package com.example.esp32app.screens

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// работа с информацией (информация не уничтожается при повороте экрана)
class MenuFragmentViewModel : ViewModel() {


    init{
        Log.e("aaa", "ViewModel start")
    }
    override fun onCleared() {
        Log.e("aaa", "ViewModel cleared")
        super.onCleared()
    }

    //private val data = MutableLiveData<String>()
    //val Live: LiveData<String> = data


    private val data: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val Live = MutableLiveData<String>()

    fun sendtext(s: String){
        Live.value = s
    }

    //fun valueseekBar(n: Int){
     //
    //}


}