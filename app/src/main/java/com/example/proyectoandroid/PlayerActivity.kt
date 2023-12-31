package com.example.proyectoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoandroid.databinding.ActivityPlayerBinding
import com.example.proyectoandroid.fragments.artistaFragment
import com.example.proyectoandroid.fragments.cancionFragment
import com.example.proyectoandroid.fragments.menuFragment
import com.example.proyectoandroid.fragments.reproductorFragment

class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_player)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frmPlayer, reproductorFragment.newInstance("Valor1","Valor2"))
            .add(R.id.frmOptionMenu, menuFragment.newInstance("Valor1","Valor2"))
            .commit()
    }
}