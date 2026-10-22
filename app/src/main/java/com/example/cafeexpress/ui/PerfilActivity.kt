package com.example.cafeexpress.ui

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafeexpress.R

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

        val btnRegresar = findViewById<ImageView>(R.id.btnRegresar)
        val txtEmail = findViewById<TextView>(R.id.txtemail)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val sharedPreferences = getSharedPreferences("CredencialesApp", Context.MODE_PRIVATE)

        val correoUsuario = sharedPreferences.getString("user_email", "Correo no disponible")

        txtEmail.text = correoUsuario

        btnCerrarSesion.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CredencialesApp", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, HomeActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}