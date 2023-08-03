package com.example.proyectoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoandroid.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_player)
    }
}