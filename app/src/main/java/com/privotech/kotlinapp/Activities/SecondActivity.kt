package com.privotech.kotlinapp.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.privotech.kotlinapp.Classes.Car
import com.privotech.kotlinapp.R
import com.privotech.kotlinapp.databinding.ActivitySecondBinding
import kotlin.random.Random

class SecondActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var binding : ActivitySecondBinding

//    lateinit var arrayList : ArrayList<Car>                                                         // Array list not initialized

    var arrayList = ArrayList<Car>()                                                                  // Array list initialized

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent : Intent = getIntent()                                                             // Getting the value from intent
        val value : Int = intent.getIntExtra("Val",0)
        Toast.makeText(this,value.toString(),Toast.LENGTH_SHORT).show()

        binding.textView.text = "Welcome" + " " + Random.nextInt(5)                             // Generating Random number

        var btn1  =  findViewById(R.id.btn1) as Button                                               // Initializing IDs
        btn1.setOnClickListener(this)

        var btn2 : Button =  findViewById(R.id.btn2)
        btn2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.btn1 ->
            {
//                arrayList = ArrayList<Car>()
                arrayList.add(Car("4","4","1300cc"))                                              // Adding elements in Model Class
                Toast.makeText(this@SecondActivity,"Button 1 Clicked",Toast.LENGTH_SHORT).show()
            }
            R.id.btn2 ->
            {
                Toast.makeText(this@SecondActivity,"Button 2 Clicked",Toast.LENGTH_SHORT).show()
            }

        }

    }

    companion object{                                                                                              // To declare Functions or Variables Static

        fun showValue(context : Context)
        {
            Toast.makeText(context,"Hi there i am static",Toast.LENGTH_SHORT).show()
        }

    }

}