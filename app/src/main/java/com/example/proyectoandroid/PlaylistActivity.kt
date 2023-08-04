package com.example.proyectoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoandroid.databinding.ActivityPlaylistBinding
import com.example.proyectoandroid.fragments.menuFragment
import com.example.proyectoandroid.fragments.playlistFragment
import com.example.proyectoandroid.fragments.songsFragment

class PlaylistActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlaylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frmPlaylist, playlistFragment.newInstance("Valor1","Valor2"))
            .add(R.id.frmOptionMenu, menuFragment.newInstance("Valor1","Valor2"))
            .commit()
    }
}