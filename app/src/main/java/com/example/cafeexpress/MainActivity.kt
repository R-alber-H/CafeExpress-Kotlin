package com.example.cafeexpress

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafeexpress.ui.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Handler(Looper.getMainLooper()).postDelayed({
            // 1. Revisamos SharedPreferences usando la misma clave "CredencialesApp"
            val sharedPreferences = getSharedPreferences("CredencialesApp", MODE_PRIVATE)
            val isLogged = sharedPreferences.getBoolean("is_logged", false)
            val userRol = sharedPreferences.getString("user_rol", "")

            val intent = if (isLogged) {
                // Si hay sesión activa, redirigimos según su rol
                if (userRol == "admin") {
                    Intent(this, com.example.cafeexpress.ui.DashboardActivity::class.java)
                } else {
                    Intent(this, HomeActivity::class.java)
                }
            } else {
                // Si no hay sesión, va directo al Home normal
                Intent(this, HomeActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 3000)
    }
}