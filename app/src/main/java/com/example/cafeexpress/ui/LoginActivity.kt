package com.example.cafeexpress.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeexpress.R
import com.example.cafeexpress.model.DatosUsuarios

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

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

        if (emailIngresado.isEmpty() || passwordIngresado.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val listaUsuarios = DatosUsuarios().obtenerUsuarios()

        val usuarioEncontrado = listaUsuarios.find {
            it.correo == emailIngresado && it.password == passwordIngresado
        }

        if (usuarioEncontrado != null) {

            val sharedPreferences = getSharedPreferences("CredencialesApp", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("user_email", usuarioEncontrado.correo)
            editor.putString("user_rol", usuarioEncontrado.rol)
            editor.putString("user_nombre", usuarioEncontrado.nombre)
            editor.putBoolean("is_logged", true)
            editor.apply()

            val intent = if (usuarioEncontrado.rol == "admin") {
                Intent(this, DashboardActivity::class.java)
            } else {
                Intent(this, HomeActivity::class.java)
            }

            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}