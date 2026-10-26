package com.example.cafeexpress.ui

import android.os.Bundle
import android.widget.ImageView
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

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // HACER QUE EL ICONO DE LA DERECHA ABRA EL MENÚ LATERAL
        val btnMenuDerecha = findViewById<ImageView>(R.id.btnMenuDerecha)
        btnMenuDerecha.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Cargar tu InicioFragment por defecto
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, InicioFragment())
                .commit()
            navigationView.setCheckedItem(R.id.nav_inicio)
        }

        // Escuchar clics del menú lateral
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_inicio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, InicioFragment())
                        .commit()
                }
                R.id.nav_contacto -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ContactoFragment())
                        .commit()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}