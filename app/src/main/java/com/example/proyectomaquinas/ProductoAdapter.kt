package com.example.proyectomaquinas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductoAdapter(private var lista: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombreProducto)
        val txtStock: TextView = view.findViewById(R.id.txtStock)
        val img: ImageView = view.findViewById(R.id.imgProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = lista[position]
        holder.txtNombre.text = p.nombre
        holder.txtStock.text = "${p.existencia} unidades"

        // Glide cargará la imagen desde la URL
        Glide.with(holder.itemView.context)
            .load(p.urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.img)
    }

    override fun getItemCount(): Int = lista.size

    fun updateList(nuevaLista: List<Producto>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}