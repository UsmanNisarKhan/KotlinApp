package com.privotech.kotlinapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.privotech.kotlinapp.Db.DataClass
import com.privotech.kotlinapp.R
import java.text.SimpleDateFormat
import java.util.*

class RecycleAdapter(var context: Context, var dataClass : List<DataClass>) : RecyclerView.Adapter<RecycleAdapter.DetailsClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsClass {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_row, parent, false)
        return DetailsClass(itemView)

    }


    override fun onBindViewHolder(holder: DetailsClass, position: Int) {

        val sdf = SimpleDateFormat("MMM dd,yyyy")
        val resultdate = Date(dataClass[position].time)

        val t_sdf = SimpleDateFormat("MMM dd,yyyy")
        val todaydate = System.currentTimeMillis()
        val today = t_sdf.format(todaydate)

        if(sdf.format(resultdate) == today) {
            holder.textView.text = "Today"
        }
        else { holder.textView.text = sdf.format(resultdate)
        }

        holder.textView1.text = dataClass[position].title
        holder.textView2.text = dataClass[position].type
        holder.textView3.text = dataClass[position].data

    }

    override fun getItemCount(): Int {

        return dataClass.size

    }

    class DetailsClass(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
            var textView: TextView = itemView.findViewById(R.id.text)
            var textView1: TextView = itemView.findViewById(R.id.text1)
            var textView2: TextView = itemView.findViewById(R.id.text2)
            var textView3: TextView = itemView.findViewById(R.id.text3)

    }
}

