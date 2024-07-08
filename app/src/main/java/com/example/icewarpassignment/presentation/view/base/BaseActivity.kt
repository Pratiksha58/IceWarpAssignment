package com.example.icewarpassignment.presentation.view.base

import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.icewarpassignment.R
import com.example.icewarpassignment.presentation.enums.Status
import com.example.icewarpassignment.presentation.utils.SharedPrefsHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handler = Handler()
        val delay = 30000 // 1000 milliseconds == 1 second
//        getLocation()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            handler.postDelayed(object : Runnable {
                override fun run() {
                    Log.d("MyHandler ", "test ")
//                    Utility.showLog("myHandler: here!") // Do your work here
                    handler.postDelayed(this, delay.toLong())
                }
            }, delay.toLong())
        }
    }
    abstract fun init()

    fun showToastMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }


}