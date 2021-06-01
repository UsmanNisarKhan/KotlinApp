package com.privotech.kotlinapp.Classes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

class Utils() {

    fun checkInternet(context: Context)
    {

        val connectivityManager : ConnectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info : NetworkInfo? = connectivityManager.activeNetworkInfo

        if(info != null && info.isConnected)
        {
            Toast.makeText(context,"Internet is connected",Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(context,"Internet is not connected",Toast.LENGTH_SHORT).show()
        }


    }


}