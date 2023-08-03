package com.example.proyectoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.ArtistRecyclerViewBinding
import com.example.proyectoandroid.databinding.ArtistTextViewBinding
import com.example.proyectoandroid.databinding.MusicCardItemBinding
import com.example.proyectoandroid.interfaces.fromUrl
import com.example.proyectoandroid.models.Songs
import retrofit2.http.Url

class artist_rv_adapter(val artistList:List<String>): RecyclerView.Adapter<artist_rv_adapter.ViewHolder>()  {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val binding = ArtistTextViewBinding.bind(view)
        fun bind(nombre: String){
            binding.tvArt.text = nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.artist_text_view,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistList[position])
    }
}