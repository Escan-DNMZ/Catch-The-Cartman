package com.escan.kendy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.escan.kendy.databinding.ActivityMain2Binding
import com.escan.kendy.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root //Layout ile çalışacağımız belirtiyor
        setContentView(view)
    }
    fun Enter(view: View){
        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }

}