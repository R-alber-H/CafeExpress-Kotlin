package com.example.cafeexpress.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeexpress.R
import com.example.cafeexpress.model.Producto
import com.google.android.material.button.MaterialButton

class ProductoAdapter(private val listaProductos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProducto: ImageView = view.findViewById(R.id.imgProducto)
        val txtNombre: TextView = view.findViewById(R.id.txtNombreProducto)
        val txtPrecio: TextView = view.findViewById(R.id.txtPrecioProducto)
        val btnComprar: MaterialButton = view.findViewById(R.id.btnComprar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = producto.precio
        holder.imgProducto.setImageResource(producto.imagenResId)

        holder.btnComprar.setOnClickListener {
            // Aquí puedes programar qué pasa cuando presionan "Comprar" en un producto
        }
    }

    override fun getItemCount(): Int = listaProductos.size
}
