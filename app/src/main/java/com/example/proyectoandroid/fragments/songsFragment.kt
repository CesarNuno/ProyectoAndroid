package com.example.proyectoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoandroid.R
import com.example.proyectoandroid.adapters.songs_rv_adapter
import com.example.proyectoandroid.databinding.SongsRecyclerViewBinding
import com.example.proyectoandroid.interfaces.ApiMusicService
import com.example.proyectoandroid.models.Canciones
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [songsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class songsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: SongsRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var listadoCanciones: MutableList<Canciones>
    private lateinit var songsRvAdapter: songs_rv_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = SongsRecyclerViewBinding.inflate(layoutInflater)
        initRecycler()
        insertSongImages()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment songsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            songsFragment().apply {
                listadoCanciones = mutableListOf()
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRecycler(){
        songsRvAdapter = songs_rv_adapter(listadoCanciones)
        binding.rvSongs.layoutManager = LinearLayoutManager(this.context)
        binding.rvSongs.adapter = songsRvAdapter
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
                        listadoCanciones.clear()
                        for (key in songs!!.result!!.asJsonArray){
                            val can: Canciones = Canciones(key.asJsonObject.get("nombre").asString,key.asJsonObject.get("duracion").asFloat,
                                key.asJsonObject.get("id").asInt,key.asJsonObject.get("album").asString,key.asJsonObject.get("ayo").asInt,
                                key.asJsonObject.get("imagen").asString,key.asJsonObject.get("audio").asString,key.asJsonObject.get("artista").asString)
                            listadoCanciones.add(can)
                        }
                    }else
                        listadoCanciones.clear()
                    songsRvAdapter.notifyDataSetChanged()

                }else{
                    listadoCanciones.clear()
                    songsRvAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}