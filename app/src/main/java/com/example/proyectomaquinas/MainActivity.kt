package com.example.proyectomaquinas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var rvProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private val listaOriginal = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProductos = findViewById(R.id.rvProducts)
        val searchView = findViewById<SearchView>(R.id.mainSearchView)

        adapter = ProductoAdapter(listaOriginal)
        rvProductos.layoutManager = LinearLayoutManager(this)
        rvProductos.adapter = adapter

        cargarProductos()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filtrar(newText ?: "")
                return true
            }
        })
    }

    private fun cargarProductos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5191/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.obtenerProductos().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    if (lista != null) {
                        listaOriginal.clear()
                        listaOriginal.addAll(lista)
                        adapter.updateList(listaOriginal)
                    }
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                android.util.Log.e("ERROR_API", t.message ?: "Error desconocido")
                Toast.makeText(this@MainActivity, "Sin conexión al servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun filtrar(texto: String) {
        val query = texto.lowercase(Locale.getDefault())
        val filtrados = listaOriginal.filter {
            it.nombre.lowercase(Locale.getDefault()).contains(query)
        }
        adapter.updateList(filtrados)
    }
}