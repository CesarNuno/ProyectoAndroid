package com.example.proyectoandroid.interfaces

import com.example.proyectoandroid.data.responses.CancionesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiMusicService {
    @GET
    suspend fun consultaMusic(@Url url:String): Response<CancionesResponse>
}