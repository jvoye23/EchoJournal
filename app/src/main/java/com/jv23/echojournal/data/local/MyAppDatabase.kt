package com.jv23.echojournal.data.local

import android.content.Context

class MyAppDatabase (context: Context) {

    fun addData(data: List<String>){
        println("Added data: ${data.size}")
    }
}