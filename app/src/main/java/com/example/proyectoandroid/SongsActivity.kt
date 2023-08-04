package com.example.proyectoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoandroid.databinding.ActivityPlayerBinding
import com.example.proyectoandroid.databinding.ActivitySongsBinding
import com.example.proyectoandroid.fragments.menuFragment
import com.example.proyectoandroid.fragments.reproductorFragment
import com.example.proyectoandroid.fragments.songsFragment

class SongsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySongsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frmSongs, songsFragment.newInstance("Valor1","Valor2"))
            .add(R.id.frmOptionMenu, menuFragment.newInstance("Valor1","Valor2"))
            .commit()
    }
}