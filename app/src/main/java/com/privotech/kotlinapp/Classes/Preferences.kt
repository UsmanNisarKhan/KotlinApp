package com.privotech.kotlinapp.Classes

import android.content.Context

class Preferences(context : Context) {                                                              // Preferences Class


    companion object{
        var u : Int = 0
    }

    val prefer = context.getSharedPreferences("Pref",Context.MODE_PRIVATE)


    fun saveString(key : String , value : String){

        val v = prefer.edit()
        v.putString(key,value)
        v.apply()

    }

    fun getString(key : String) : String?{

        return prefer.getString(key,"")

    }

    fun saveBoolean(key : String , status : Boolean)
    {
        val b = prefer.edit()
        b.putBoolean(key,status)
        b.apply()
    }

    fun getBoolean(key : String) : Boolean
    {
        return prefer.getBoolean(key,false)
    }

}