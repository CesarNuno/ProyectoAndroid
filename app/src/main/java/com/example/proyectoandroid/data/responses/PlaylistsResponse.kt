package com.example.proyectoandroid.data.responses

import retrofit2.http.Url

data class PlaylistsResponse(
    val nombre:String,
    val id:Int,
    val canciones:Any)
