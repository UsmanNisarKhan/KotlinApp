package com.privotech.kotlinapp.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.privotech.kotlinapp.databinding.ActivityRequestPermissionBinding
import java.util.ArrayList

class RequestPermission : AppCompatActivity() {

    lateinit var binding : ActivityRequestPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRequestPermissionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        validatePermission()
    }

    fun validatePermission()
    {
        val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        Permissions.check(
            this , permissions, null, null, object : PermissionHandler() {
                override fun onGranted() {

                    val intent = Intent(this@RequestPermission, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onDenied(context: Context?, deniedPermissions: ArrayList<String?>?) {

                    validatePermission()
                }
            })
    }
}