package com.privotech.kotlinapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.privotech.kotlinapp.Db.CarClass
import com.privotech.kotlinapp.R

class CarAdapter(var context: Context, var carClass: List<CarClass>) : RecyclerView.Adapter<CarAdapter.ViewCarDetails>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewCarDetails {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row, parent, false)
        return CarAdapter.ViewCarDetails(itemView)

    }

    override fun onBindViewHolder(holder: ViewCarDetails, position: Int) {

        holder.textView1.text = carClass[position].tyres
        holder.textView2.text = carClass[position].doors
        holder.textView3.text = carClass[position].power

    }

    override fun getItemCount(): Int {
        return carClass.size
    }



    class ViewCarDetails(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
            var textView1 : TextView = itemView.findViewById(R.id.text1)
            var textView2 : TextView = itemView.findViewById(R.id.text2)
            var textView3 : TextView = itemView.findViewById(R.id.text3)
    }


}