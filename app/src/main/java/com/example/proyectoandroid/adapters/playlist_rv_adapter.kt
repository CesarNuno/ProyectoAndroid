package com.example.proyectoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.ArtistTextViewBinding
import com.example.proyectoandroid.databinding.PlaylistTextViewBinding

class playlist_rv_adapter(val playlistList:List<String>): RecyclerView.Adapter<playlist_rv_adapter.ViewHolder>()  {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = PlaylistTextViewBinding.bind(view)
        fun bind(nombre: String){
            binding.txtPlaylist.text = nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.playlist_text_view,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return playlistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playlistList[position])
    }
}