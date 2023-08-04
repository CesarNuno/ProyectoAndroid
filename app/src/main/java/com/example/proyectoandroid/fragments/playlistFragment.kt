package com.example.proyectoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoandroid.R
import com.example.proyectoandroid.adapters.artist_rv_adapter
import com.example.proyectoandroid.adapters.playlist_rv_adapter
import com.example.proyectoandroid.adapters.songs_rv_adapter
import com.example.proyectoandroid.databinding.ArtistRecyclerViewBinding
import com.example.proyectoandroid.databinding.FragmentMenuBinding
import com.example.proyectoandroid.databinding.PlaylistRecyclerViewBinding
import com.example.proyectoandroid.interfaces.ApiMusicService
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
 * Use the [playlistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class playlistFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: PlaylistRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var playlistRvAdapter: playlist_rv_adapter
    private lateinit var listadoPlaylist: MutableList<String>

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
        _binding = PlaylistRecyclerViewBinding.inflate(layoutInflater)
        initRecycler()
        insertPlaylists()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment playlistFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            playlistFragment().apply {
                listadoPlaylist = mutableListOf()
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRecycler(){
        playlistRvAdapter = playlist_rv_adapter(listadoPlaylist)
        binding.rvPlaylist.layoutManager = LinearLayoutManager(this.context)
        binding.rvPlaylist.adapter = playlistRvAdapter
    }

    private fun getRetrofit(base_url_resource:Int): Retrofit {
        return Retrofit.Builder()
            .baseUrl(resources.getString(base_url_resource))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun insertPlaylists(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit(R.string.api_url_songs).create(ApiMusicService::class.java).consultaMusic("listas/")
            val playlist = response.body()
            activity?.runOnUiThread{
                if(response.isSuccessful){
                    if(playlist?.result?.size() != 0){
                        listadoPlaylist.clear()
                        for (key in playlist!!.result!!.asJsonArray){
                            val art:String = key.asJsonObject.get("nombre").asString
                            listadoPlaylist.add(art)
                        }
                    }else
                        listadoPlaylist.clear()
                    playlistRvAdapter.notifyDataSetChanged()

                }else{
                    listadoPlaylist.clear()
                    playlistRvAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}