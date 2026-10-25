package com.example.cafeexpress.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.cafeexpress.InicioFragment
import com.google.android.material.navigation.NavigationView
import com.example.cafeexpress.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // 1. Configurar la barra superior (Toolbar)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 2. Vincular el DrawerLayout y el NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // 3. Crear el botón hamburguesa para abrir/cerrar el menú
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 4. Cargar tu InicioFragment por defecto al abrir la pantalla
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, InicioFragment())
                .commit()
            navigationView.setCheckedItem(R.id.nav_inicio)
        }

        // 5. Escuchar los clics del menú lateral
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_inicio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, InicioFragment())
                        .commit()
                }
                // Aquí podrás agregar más opciones en el futuro (como Contactos o Salarios)
            }
            // Cerrar el menú automáticamente al tocar una opción
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}