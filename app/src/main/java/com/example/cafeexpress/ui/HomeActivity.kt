package com.example.cafeexpress.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout
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

        val sharedPreferences = getSharedPreferences("CredencialesApp", Context.MODE_PRIVATE)
        val userIsLogged = sharedPreferences.getBoolean("is_logged", false)

        setupRecyclerViewProductos()
        is_logged((userIsLogged))
    }

    private fun setupRecyclerViewProductos() {
        val recycler = findViewById<RecyclerView>(R.id.recyclerProductos)

        // 1. Configuramos el diseño horizontal
        recycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Obtenemos los productos desde nuestra fuente de datos
        val fuenteDeDatos = DatosProductos()
        val listaDeProductos = fuenteDeDatos.obtenerProductosPopulares()

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 3. Asignamos el adaptador
        recycler.adapter = ProductoAdapter(listaDeProductos)


    }

    private fun is_logged(userIsLogged : Boolean){
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val icPerfil = findViewById<ImageView>(R.id.icPerfil)

        if(userIsLogged){
            btnIniciarSesion.visibility = View.GONE
            icPerfil.visibility = View.VISIBLE
        }
    }
}