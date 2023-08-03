package com.example.proyectoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoandroid.R
import com.example.proyectoandroid.adapters.artist_rv_adapter
import com.example.proyectoandroid.adapters.music_rv_adapter
import com.example.proyectoandroid.databinding.ArtistRecyclerViewBinding
import com.example.proyectoandroid.databinding.MusicRecyclerViewBinding
import com.example.proyectoandroid.interfaces.ApiMusicService
import com.example.proyectoandroid.models.Artistas
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
 * Use the [artistaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class artistaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: ArtistRecyclerViewBinding? = null
    private val HLayoutManager =  LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
    private val binding get() = _binding!!
    private lateinit var listadoArtistas: MutableList<String>
    private lateinit var artistRvAdapter: artist_rv_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listadoArtistas = mutableListOf()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        _binding = ArtistRecyclerViewBinding.inflate(layoutInflater)
        binding.rvArtist.layoutManager = HLayoutManager
        initRecycler()
        insertArtists()
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun initRecycler(){
        artistRvAdapter = artist_rv_adapter(listadoArtistas)

        binding.rvArtist.adapter = artistRvAdapter
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            artistaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun getRetrofit(base_url_resource:Int): Retrofit {
        return Retrofit.Builder()
            .baseUrl(resources.getString(base_url_resource))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun insertArtists(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit(R.string.api_url_songs).create(ApiMusicService::class.java).consultaMusic("canciones")
            val artistas = response.body()
            activity?.runOnUiThread{
                if(response.isSuccessful){
                    if(artistas?.result?.size() != 0){
                        listadoArtistas.clear()
                        for (key in artistas!!.result!!.asJsonArray){
                            val art:String = key.asJsonObject.get("nombre").asString
                            listadoArtistas.add(art)
                        }
                    }else
                        listadoArtistas.clear()
                    artistRvAdapter.notifyDataSetChanged()

                }else{
                    listadoArtistas.clear()
                    artistRvAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}