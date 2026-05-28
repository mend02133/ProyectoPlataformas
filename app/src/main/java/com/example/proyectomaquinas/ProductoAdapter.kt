package com.example.proyectomaquinas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private var lista: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombreProducto)
        val txtPrecio: TextView = view.findViewById(R.id.txtStock)
        val txtDescripcion: TextView = view.findViewById(R.id.txtDescripcion)
        val txtUbicacion: TextView = view.findViewById(R.id.txtUbicacion)
        val txtEstado: TextView = view.findViewById(R.id.txtEstado)
        val txtCantidad: TextView = view.findViewById(R.id.txtCantidad)
        val img: ImageView = view.findViewById(R.id.imgProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = lista[position]

        holder.txtNombre.text = p.nombre
        holder.txtPrecio.text = "$ ${p.precio}"
        holder.txtDescripcion.text = "Descripción: ${p.descripcion}"
        holder.txtUbicacion.text = "Ubicación: ${p.ubicacion}"
        holder.txtEstado.text = "Estado: ${p.estado}"
        holder.txtCantidad.text = "Cantidad: ${p.cantidad}"

        val imagenProducto = when (p.nombre.lowercase().trim()) {
            "doritos" -> R.drawable.doritos
            "ruffles" -> R.drawable.ruffles
            "cheetos" -> R.drawable.cheetos
            "mantencadas" -> R.drawable.mantencadas
            "galletas emperador" -> R.drawable.galletas_emperador
            "galletas chokis" -> R.drawable.galletas_chokis
            "submarinos" -> R.drawable.submarinos
            "pingüinos" -> R.drawable.pinguinos
            "chocorroles" -> R.drawable.chocorroles
            "gansito" -> R.drawable.gansito
            "nito" -> R.drawable.nito
            "coca_cola" -> R.drawable.coca_cola
            "fanta" -> R.drawable.fanta
            "sprite" -> R.drawable.sprite
            "jugo_valle" -> R.drawable.jugo_valle
            "electrolit" -> R.drawable.electrolit
            "jumex_durazno" -> R.drawable.jumex_durazno
            "jumex_mango" -> R.drawable.jumex_mango
            "fresca" -> R.drawable.fresca



            else -> android.R.drawable.ic_menu_gallery
        }

        holder.img.setImageResource(imagenProducto)
    }

    override fun getItemCount(): Int = lista.size

    fun updateList(nuevaLista: List<Producto>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}