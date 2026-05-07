package com.example.proyectoplataformas

import android.os.Bundle
import android.widget.TextView
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
import androidx.activity.enableEdgeToEdge
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var rvProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private val listaOriginal = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // --- Configuración del Front (RecyclerView) ---
        rvProductos = findViewById(R.id.rvProducts)
        val searchView = findViewById<SearchView>(R.id.mainSearchView)

        // Datos de prueba
        listaOriginal.add(Producto("Papas Sabritas", "Facultad Ingeniería", 15, "https://via.placeholder.com/150"))
        listaOriginal.add(Producto("Coca Cola 600ml", "Cooperativa", 8, "https://via.placeholder.com/150"))
        listaOriginal.add(Producto("Galletas", "Laboratorios", 20, "https://via.placeholder.com/150"))

        adapter = ProductoAdapter(listaOriginal)
        rvProductos.layoutManager = LinearLayoutManager(this)
        rvProductos.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                filtrar(newText ?: "")
                return true
            }
        })

        // --- Configuración del Back (Retrofit) ---
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/android_api/") // Tu carpeta en htdocs
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.obtenerUsuarios().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    val txtDatos = findViewById<TextView>(R.id.txtDatos)

                    val nombres = lista?.joinToString(", ") { it.nombre }
                    txtDatos.text = "Usuarios: $nombres"
                }
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                android.util.Log.e("ERROR_DB", t.message ?: "Error inesperado")
                Toast.makeText(this@MainActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun filtrar(texto: String) {
        val filtrados = listaOriginal.filter {
            it.nombre.lowercase().contains(texto.lowercase())
        }
        adapter.updateList(filtrados)
    }
}
