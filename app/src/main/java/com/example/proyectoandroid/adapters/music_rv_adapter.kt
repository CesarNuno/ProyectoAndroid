package com.example.proyectoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.MusicCardItemBinding
import com.example.proyectoandroid.interfaces.fromUrl
import com.example.proyectoandroid.models.Songs
import retrofit2.http.Url

class music_rv_adapter(val musicList:List<String>): RecyclerView.Adapter<music_rv_adapter.ViewHolder>()  {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val binding = MusicCardItemBinding.bind(view)
        fun bind(image: String){
            binding.albumImage.fromUrl(image)
            binding.relLayoutCardMusic.setBackgroundColor(0x00000000)
            binding.relLayoutCardMusic2.setBackgroundColor(0x00000000)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.music_card_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(musicList[position])
    }
}