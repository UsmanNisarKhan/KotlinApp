package com.privotech.kotlinapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.privotech.kotlinapp.Adapter.RecycleAdapter
import com.privotech.kotlinapp.Classes.Preferences
import com.privotech.kotlinapp.Classes.User
import com.privotech.kotlinapp.Db.ApplicationDatabase
import com.privotech.kotlinapp.Db.DataClass
import com.privotech.kotlinapp.Classes.Preferences.Companion.u
import com.privotech.kotlinapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var recycleAdapter : RecycleAdapter
    @Inject
    lateinit var handler : Handler
    @Inject
    lateinit var preferences: Preferences
    @Inject
    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val dataClass = DataClass()
                dataClass.title = "Name"
                dataClass.type = "1"
                dataClass.data = "Body"


                val b = ApplicationDatabase.getDatabase(this@MainActivity)

                b?.setDao()?.insert(dataClass)

                withContext(Dispatchers.Main)
                {
//                    user = User("")
                    user.name = "Usman"
                    Toast.makeText(this@MainActivity,user.name,Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity,"I am added",Toast.LENGTH_SHORT).show()
                }

            }

            handler.postDelayed(Runnable {

                Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()

            },2000)

        })


        val b = ApplicationDatabase.getDatabase(this@MainActivity)

        b?.setDao()?.getAll()?.observe(this@MainActivity, Observer {list ->

            binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            recycleAdapter = RecycleAdapter(this,list)
            binding.recyclerView.adapter = recycleAdapter

        })


        preferences.saveString("Main","VALUE")
        val t = preferences.getString("Main")

        Toast.makeText(this,t,Toast.LENGTH_SHORT).show()

        u = 0


        Toast.makeText(this,u.toString(),Toast.LENGTH_SHORT).show()

    }
}