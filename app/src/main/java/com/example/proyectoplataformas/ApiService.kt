package com.example.proyectoplataformas

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("consultar.php")
    fun obtenerUsuarios(): Call<List<Usuario>>
}
