package com.example.proyectomaquinas

data class Producto(
    val idProductos: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val ubicacion: String,
    val estado : String,
    val cantidad: Int
)