package com.example.proyectoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoandroid.MainActivity
import com.example.proyectoandroid.R
import com.example.proyectoandroid.adapters.music_rv_adapter
import com.example.proyectoandroid.databinding.ActivityMainBinding
import com.example.proyectoandroid.databinding.MusicCardItemBinding
import com.example.proyectoandroid.databinding.MusicRecyclerViewBinding
import com.example.proyectoandroid.models.Songs

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
    private val binding get() = _binding!!

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
        val layoutManager =  LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        this._binding = MusicRecyclerViewBinding.inflate(layoutInflater,container,false)
        binding.rvMusic.layoutManager = layoutManager
        binding.rvMusic.adapter = music_rv_adapter(
        listOf(
            Songs(resources.getDrawable(R.drawable.inaugh,null),"Ina"),
            Songs(resources.getDrawable(R.drawable.inaugh,null),"Ina"),
            Songs(resources.getDrawable(R.drawable.inaugh,null),"Ina"),
            Songs(resources.getDrawable(R.drawable.inaugh,null),"Ina"),
        )
        )
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment cancionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            cancionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}