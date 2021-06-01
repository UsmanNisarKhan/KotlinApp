package com.privotech.kotlinapp.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.privotech.kotlinapp.Activities.SecondActivity.Companion.showValue
import com.privotech.kotlinapp.Adapter.RecycleAdapter
import com.privotech.kotlinapp.Classes.Car
import com.privotech.kotlinapp.Classes.Preferences
import com.privotech.kotlinapp.Classes.Preferences.Companion.u
import com.privotech.kotlinapp.Classes.User
import com.privotech.kotlinapp.Classes.Utils
import com.privotech.kotlinapp.Db.ApplicationDatabase
import com.privotech.kotlinapp.Db.DataClass
import com.privotech.kotlinapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    lateinit var binding : ActivityMainBinding                                                      // View Binding
    lateinit var recycleAdapter : RecycleAdapter
    @Inject
    lateinit var handler : Handler                                                                  //  Injected
    @Inject
    lateinit var preferences: Preferences                                                           //  Injected
    @Inject
    lateinit var user : User                                                                        //  Injected

//    lateinit var user : User                                                                      // Not Injected

    @Inject
    lateinit var utils :Utils                                                                       //  Injected


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)                                                                // Set the View

        binding.button1.setOnClickListener(View.OnClickListener {


            CoroutineScope(Dispatchers.IO).launch {

                val dataClass = DataClass()                                                         // Coroutine in Background
                dataClass.title = "Name"
                dataClass.type = "1"
                dataClass.data = "Body"


                val b = ApplicationDatabase.getDatabase(this@MainActivity)

                b?.setDao()?.insert(dataClass)                                                      //Inserting data in Database


                withContext(Dispatchers.Main)                                                       // Coroutine on Main
                {
//                    user = User("")                                                                           // Accessing Object without Inject
                    user.name = "Usman"                                                                         // Accessing Object with Inject
                    Toast.makeText(this@MainActivity,user.name,Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity,"I am added",Toast.LENGTH_SHORT).show()

                    showValue(this@MainActivity)                                                        // Static func from another Activity

                    utils.checkInternet(this@MainActivity)                                              // Accessing function with Inject

                }

            }


            handler.postDelayed(Runnable {                                                                     // Handler Object with Inject

                Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()

            },2000)

        })


        val b = ApplicationDatabase.getDatabase(this@MainActivity)                                    // LiveData

        b?.setDao()?.getAll()?.observe(this@MainActivity, Observer {list ->

            binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            recycleAdapter = RecycleAdapter(this, list)
            binding.recyclerView.adapter = recycleAdapter

        })


        preferences.saveString("Main","VALUE")                                                              // Preferences object using Inject
        val t = preferences.getString("Main")
        Toast.makeText(this,t,Toast.LENGTH_SHORT).show()


        u = 0                                                                                               // Static variable from another class
        Toast.makeText(this,u.toString(),Toast.LENGTH_SHORT).show()


        binding.button2.setOnClickListener(View.OnClickListener {

            val intent = Intent(this,SecondActivity::class.java)                              // Intent with Data
            intent.putExtra("Val",1)
            startActivity(intent)
            finish()

//          startActivity(Intent(this@MainActivity,SecondActivity::class.java))                            // Intent in single line

        })

    }
}