package com.example.proyectoandroid.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoandroid.MainActivity
import com.example.proyectoandroid.R
import com.example.proyectoandroid.adapters.music_rv_adapter
import com.example.proyectoandroid.databinding.MusicRecyclerViewBinding
import com.example.proyectoandroid.interfaces.ApiMusicService
import com.example.proyectoandroid.models.Canciones
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cancionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class cancionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: MusicRecyclerViewBinding? = null
    private lateinit var listadoCanciones: MutableList<Canciones>
    private val binding get() = _binding!!
    private val songImages = mutableListOf<String>()
    private lateinit var musicRvAdapter: music_rv_adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listadoCanciones = mutableListOf()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = MusicRecyclerViewBinding.inflate(layoutInflater)
        initRecycler()
        insertSongImages()
        return binding.root

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            cancionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun initRecycler(){
        val HLayoutManager =  LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        musicRvAdapter = music_rv_adapter(songImages)
        binding.rvMusic.layoutManager = HLayoutManager
        binding.rvMusic.adapter = musicRvAdapter
    }

    private fun getRetrofit(base_url_resource:Int): Retrofit {
        return Retrofit.Builder()
            .baseUrl(resources.getString(base_url_resource))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun insertSongImages(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit(R.string.api_url_songs).create(ApiMusicService::class.java).consultaMusic("canciones")
            val songs = response.body()
            activity?.runOnUiThread{
                if(response.isSuccessful){
                    if(songs?.result?.size() != 0){
                        songImages.clear()
                        for (key in songs!!.result!!.asJsonArray){
                            val can: Canciones = Canciones(key.asJsonObject.get("nombre").asString,key.asJsonObject.get("duracion").asFloat,
                                key.asJsonObject.get("id").asInt,key.asJsonObject.get("album").asString,key.asJsonObject.get("ayo").asInt,
                                key.asJsonObject.get("imagen").asString,key.asJsonObject.get("audio").asString,key.asJsonObject.get("artista").asString)
                            listadoCanciones.add(can)
                        }
                        for (item in listadoCanciones){
                            item.imagen?.let { songImages.add(it) }
                        }
                    }else
                        songImages.clear()
                    musicRvAdapter.notifyDataSetChanged()

                }else{
                    songImages.clear()
                    musicRvAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}