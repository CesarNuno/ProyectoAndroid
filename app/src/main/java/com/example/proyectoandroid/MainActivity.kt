package com.example.proyectoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoandroid.databinding.ActivityMainBinding
import com.example.proyectoandroid.databinding.MusicRecyclerViewBinding
import com.example.proyectoandroid.fragments.artistaFragment
import com.example.proyectoandroid.fragments.cancionFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frmMusic, cancionFragment.newInstance("Valor1","Valor2"))
            .add(R.id.frmArtist, artistaFragment.newInstance("Valor1","Valor2"))
            .commit()
    }
}