package com.privotech.kotlinapp.Activities

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.privotech.kotlinapp.Adapter.CarAdapter
import com.privotech.kotlinapp.Classes.Car
import com.privotech.kotlinapp.Classes.Preferences
import com.privotech.kotlinapp.Db.ApplicationDatabase
import com.privotech.kotlinapp.Db.CarClass
import com.privotech.kotlinapp.R
import com.privotech.kotlinapp.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var binding: ActivitySecondBinding

    lateinit var carAdapter: CarAdapter

    @Inject
    lateinit var preferences: Preferences

//    lateinit var arrayList : ArrayList<Car>                                                         // Array list not initialized

    var arrayList = ArrayList<Car>()                                                                  // Array list initialized

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!preferences.getBoolean("Switch")) {
            setTheme(R.style.LightTheme)
        } else {
            setTheme(R.style.DarkTheme)
        }

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = getIntent()                                                             // Getting the value from intent
        val value: Int = intent.getIntExtra("Val",0)
        Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show()


        val db = ApplicationDatabase.getDatabase(this@SecondActivity)
        db?.carDao()?.getAll()?.observe(this, Observer {

            binding.recyclerView.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            carAdapter = CarAdapter(this, it)
            binding.recyclerView.adapter = carAdapter

        })


        binding.textView.text = "Welcome" + " " + Random.nextInt(5)                             // Generating Random number

        val btn1 = findViewById<Button>(R.id.btn1)                                                   // Initializing IDs
        btn1.setOnClickListener(this)

        val btn2: Button = findViewById(R.id.btn2)
        btn2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn1 -> {
//                arrayList = ArrayList<Car>()
                arrayList.add(Car("4", "4", "1300"))                                // Adding elements in Model Class

                CoroutineScope(Dispatchers.IO).launch {

                    val carClass = CarClass()
                    carClass.tyres = "4"
                    carClass.doors = "4"
                    carClass.power = "1300"

                    val db = ApplicationDatabase.getDatabase(this@SecondActivity)
                    db?.carDao()?.insert(carClass)
                }


                Toast.makeText(this@SecondActivity, "Button 1 Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.btn2 -> {
                Toast.makeText(this@SecondActivity, "Button 2 Clicked", Toast.LENGTH_SHORT).show()
            }

        }

    }

    companion object {                                                                               // To declare Functions or Variables Static

        fun showValue(context: Context) {
            Toast.makeText(context, "Hi there i am static", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {

        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }

}