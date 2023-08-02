package com.example.proyectoandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.databinding.MusicCardItemBinding
import com.example.proyectoandroid.models.Songs

class music_rv_adapter(val musicList:List<Songs>): RecyclerView.Adapter<music_rv_adapter.ViewHolder>()  {
    inner class ViewHolder(val binding: MusicCardItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MusicCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(musicList[position]) {
                binding.albumImage.setImageDrawable(this.Image)
                binding.songName.text = this.Name
            }
        }
    }
}