package com.example.proyectomaquinas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        // Las URLs son ejemplos de productos reales para que se vea mejor
        listaOriginal.add(Producto("Papas Sabritas", "Facultad Ingeniería", 15,
            "https://images.openfoodfacts.org/images/products/007/501/009/9633/front_es.3.400.jpg", "Snacks"))

        listaOriginal.add(Producto("Coca Cola 600ml", "Cooperativa", 3,
            "https://images.openfoodfacts.org/images/products/005/000/112/8082/front_es.3.400.jpg", "Bebidas"))

        listaOriginal.add(Producto("Café Americano", "Laboratorios", 20,
            "https://images.openfoodfacts.org/images/products/002/010/006/5502/front_es.3.400.jpg", "Bebidas"))

        listaOriginal.add(Producto("Galletas", "Biblioteca", 10,
            "https://images.openfoodfacts.org/images/products/007/622/200/2571/front_es.3.400.jpg", "Papelería"))

        listaOriginal.add(Producto("Sándwich", "Cooperativa", 4,
            "https://images.openfoodfacts.org/images/products/005/449/136/8014/front_es.3.400.jpg", "Comida"))


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
    }

    private fun filtrar(texto: String) {
        val query = texto.lowercase(Locale.getDefault())
        val filtrados = listaOriginal.filter {
            it.nombre.lowercase(Locale.getDefault()).contains(query) ||
                    it.categoria.lowercase(Locale.getDefault()).contains(query)
        }
        adapter.updateList(filtrados)
    }
}