package com.example.proyectoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.MusicCardItemBinding
import com.example.proyectoandroid.databinding.SongsCardItemBinding
import com.example.proyectoandroid.interfaces.fromUrl
import com.example.proyectoandroid.models.Canciones

class songs_rv_adapter(val songList:List<Canciones>): RecyclerView.Adapter<songs_rv_adapter.ViewHolder>()   {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val binding = SongsCardItemBinding.bind(view)
        fun bind(image: String?,name:String?, artist:String?){
            if (image != null) {
                binding.ivSongImage.fromUrl(image)
            }
            binding.txtSongName.text = name
            binding.txtArtistName.text = artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.songs_card_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songList[position].imagen,songList[position].nombre,songList[position].artista)
    }
}