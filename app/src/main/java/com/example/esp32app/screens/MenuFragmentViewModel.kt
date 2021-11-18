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

    //private var data = MutableLiveData<String>()
    //val Live: LiveData<String> = data

    private var seekdata = MutableLiveData<Int>()
    val LiveSeek: LiveData<Int> = seekdata

    private var swichdata = MutableLiveData<List<String>>()
    val LiveSwich: LiveData<List<String>> = swichdata

    //private val data: MutableLiveData<String> by lazy {
    //    MutableLiveData<String>()
    //}

    //val Live = MutableLiveData<String>()


    fun sendseek(s: Int){
        seekdata.value = s
    }

    fun sendswich(s: String, t: String){
        swichdata.value = listOf(s,t)
    }


    //fun valueseekBar(n: Int){
     //
    //}


}