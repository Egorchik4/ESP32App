package com.example.esp32app.screens

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// работа с информацией (информация не уничтожается при повороте экрана)
class MenuFragmentViewModel : ViewModel() {

    // Создание LiveData

    // для верхнего seekbar
    private var seekdataup = MutableLiveData<Int>()
    val LiveSeekBarUp: LiveData<Int> = seekdataup

    // для нижнего seekbar
    private var seekdatadown = MutableLiveData<Int>()
    val LiveSeekBarDown: LiveData<Int> = seekdatadown

    // для swichLed
    private var swichled = MutableLiveData<List<String>>()
    val LedSwich: LiveData<List<String>> = swichled

    // для swich мотора в одну сторону
    private var swichone = MutableLiveData<List<String>>()
    val MOneSwich: LiveData<List<String>> = swichone

    // для swich мотора в другую сторону
    private var swichtwo = MutableLiveData<List<String>>()
    val MTwoSwich: LiveData<List<String>> = swichtwo



    // для нижнего seekbar
    fun sendseekdown(s: Int){
        seekdatadown.value = s
    }

    // для верхнего seekbar
    fun sendseekup(s: Int){
        seekdataup.value = s
    }

    // для переключателя swich
    fun sendswich(s: String, t: String){
        swichled.value = listOf(s,t)
    }


    // для переключателя swich
    fun oneswich(s: String, t: String){
        swichone.value = listOf(s,t)
    }

    // для переключателя swich
    fun twoswich(s: String, t: String){
        swichtwo.value = listOf(s,t)
    }


}