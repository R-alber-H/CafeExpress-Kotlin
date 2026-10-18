package com.example.cafeexpress.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cafeexpress.R

class LoginActivity : AppCompatActivity() {

    private val correoFijo = "usuario1@gmail.com"
    private val passwordFijo = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            verificarCredenciales(etEmail, etPassword)
        }
    }

    private fun verificarCredenciales(etEmail: EditText, etPassword: EditText) {
        val emailIngresado = etEmail.text.toString().trim()
        val passwordIngresado = etPassword.text.toString().trim()

        if (emailIngresado == correoFijo && passwordIngresado == passwordFijo) {

            val sharedPreferences = getSharedPreferences("CredencialesApp", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("user_email", emailIngresado)
            editor.putBoolean("is_logged", true)
            editor.apply()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}