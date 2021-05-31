package com.privotech.kotlinapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.privotech.kotlinapp.Db.DataClass
import com.privotech.kotlinapp.R

class RecycleAdapter(context: Context, var dataClass : List<DataClass>) : RecyclerView.Adapter<RecycleAdapter.DetailsClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsClass {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row, parent, false)
        return DetailsClass(itemView)

    }


    override fun onBindViewHolder(holder: DetailsClass, position: Int) {

        holder.textView1.text = dataClass[position].title
        holder.textView2.text = dataClass[position].type
        holder.textView3.text = dataClass[position].data

    }

    override fun getItemCount(): Int {

        return dataClass.size

    }

    class DetailsClass(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
            var textView1: TextView = itemView.findViewById(R.id.text1)
            var textView2: TextView = itemView.findViewById(R.id.text2)
            var textView3: TextView = itemView.findViewById(R.id.text3)

    }
}

