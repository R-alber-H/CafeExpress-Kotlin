package com.example.cafeexpress.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeexpress.R
import com.example.cafeexpress.adapter.ProductoAdapter
import com.example.cafeexpress.model.DatosProductos
import com.example.cafeexpress.model.Producto

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerViewProductos()
    }

    // 💡 Esta función va FUERA de onCreate, al mismo nivel
    private fun setupRecyclerViewProductos() {
        val recycler = findViewById<RecyclerView>(R.id.recyclerProductos)

        // 1. Configuramos el diseño horizontal
        recycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Obtenemos los productos desde nuestra fuente de datos (DataSource)
        val fuenteDeDatos = DatosProductos()
        val listaDeProductos = fuenteDeDatos.obtenerProductosPopulares()

        // 3. Asignamos el adaptador
        recycler.adapter = ProductoAdapter(listaDeProductos)
    }
}