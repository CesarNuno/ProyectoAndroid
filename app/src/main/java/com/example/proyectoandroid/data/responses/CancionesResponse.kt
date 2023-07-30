package com.example.proyectoandroid.data.responses

import retrofit2.http.Url

data class CancionesResponse(
    val nombre :String,
    val duracion:Float,
    val id:Int,
    val album:String,
    val ayo:Int,
    val imagen:Url,
    val audio:Url,
    val artisa:String
    )
