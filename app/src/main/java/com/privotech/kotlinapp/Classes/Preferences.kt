package com.privotech.kotlinapp.Classes

import android.content.Context

class Preferences(context : Context) {                                                              // Preferences Class


    companion object{
        var u : Int = 0
    }

    val prefer = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE)


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

    fun savaInt(key: String, value: Int)
    {
        val i = prefer.edit()
        i.putInt(key,value)
        i.apply()
    }

    fun getInt(key: String) : Int{

        return prefer.getInt(key,0)
    }

    fun savaLong(key: String, value: Long)
    {
        val i = prefer.edit()
        i.putLong(key,value)
        i.apply()
    }

    fun getLong(key: String) : Long{

        return prefer.getLong(key,0)
    }

}