package com.privotech.kotlinapp.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.privotech.kotlinapp.Activities.SecondActivity.Companion.showValue
import com.privotech.kotlinapp.Adapter.RecycleAdapter
import com.privotech.kotlinapp.CircularMenu_Java.OnMenuSelectedListener
import com.privotech.kotlinapp.CircularMenu_Java.OnMenuStatusChangeListener
import com.privotech.kotlinapp.Classes.*
import com.privotech.kotlinapp.Classes.Preferences.Companion.u
import com.privotech.kotlinapp.Db.ApplicationDatabase
import com.privotech.kotlinapp.Db.DataClass
import com.privotech.kotlinapp.R
import com.privotech.kotlinapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
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

        if (!preferences.getBoolean("Switch")) {
            setTheme(R.style.LightTheme)
        } else {
            setTheme(R.style.DarkTheme)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)                                                                // Set the View

//        if(preferences.getBoolean("Switch"))
//        {
//
//        }

        binding.toggleSwitch.isChecked = preferences.getBoolean("Switch")

        binding.toggleSwitch.setOnClickListener(View.OnClickListener {

            if(binding.toggleSwitch.isChecked)
            {
                preferences.saveBoolean("Switch",true)
                binding.toggleSwitch.isChecked = true
            }
            else
            {
                preferences.saveBoolean("Switch",false)
                binding.toggleSwitch.isChecked = false
            }

            startActivity(Intent(this@MainActivity, this@MainActivity.javaClass).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))

        })


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



//        var dialog = Dialog(this)                                                                     // Dialog
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.activity_main)
//        dialog.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_launcher_background))
//
//        var btn1 = dialog.findViewById(R.id.btn1) as Button
//        var btn2 : Button = dialog.findViewById(R.id.btn2)
//
//        btn1.setOnClickListener(View.OnClickListener {
//
//        })
//
//        btn2.setOnClickListener(View.OnClickListener {
//
//        })
//
//        dialog.show()



        binding.circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),                          //  Circular Menu
            R.drawable.ic_baseline_menu_24, R.drawable.ic_baseline_close_24)
            .addSubMenu(Color.parseColor("#258CFF"), R.drawable.ic_baseline_menu_24)
            .addSubMenu(Color.parseColor("#30A400"), R.drawable.ic_baseline_menu_24)
            .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.ic_baseline_menu_24)
            .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.ic_baseline_menu_24)
            .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.ic_baseline_menu_24)
            .setOnMenuSelectedListener(OnMenuSelectedListener {

                if (it == 1) {
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                }


            }).setOnMenuStatusChangeListener(object :
                OnMenuStatusChangeListener {
                override fun onMenuOpened() {}
                override fun onMenuClosed() {}
            })

        }


            override fun onMenuOpened(featureId : Int , menu : Menu) : Boolean{

                binding.circleMenu.openMenu()
                return super.onMenuOpened(featureId, menu)
        }
}

