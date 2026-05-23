package com.example.proyectomaquinas

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("consultar.php")
    fun obtenerProductos(): Call<List<Producto>>
}