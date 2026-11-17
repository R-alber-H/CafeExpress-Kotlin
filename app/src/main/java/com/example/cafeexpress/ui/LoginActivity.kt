package com.example.cafeexpress.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeexpress.R
// Importaciones de Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    // Declaramos instancias de Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Inicializamos Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtRegister = findViewById<TextView>(R.id.txtRegister)

        btnLogin.setOnClickListener {
            verificarCredenciales(etEmail, etPassword)
        }

        txtRegister.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun verificarCredenciales(etEmail: EditText, etPassword: EditText) {
        val emailIngresado = etEmail.text.toString().trim()
        val passwordIngresado = etPassword.text.toString().trim()

        if (emailIngresado.isEmpty() || passwordIngresado.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // 1. Autenticar con Firebase Authentication (Email y Contraseña)
        auth.signInWithEmailAndPassword(emailIngresado, passwordIngresado)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid

                    if (userId != null) {
                        // 2. Consultar el rol en Cloud Firestore usando el UID del usuario
                        db.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                if (document.exists()) {
                                    val nombre = document.getString("nombre") ?: "Usuario"
                                    val rol = document.getString("rol") ?: "cliente"
                                    val correo = document.getString("email") ?: emailIngresado

                                    // 3. Guardar sesión localmente en SharedPreferences
                                    val sharedPreferences = getSharedPreferences("CredencialesApp", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putString("user_email", correo)
                                    editor.putString("user_rol", rol)
                                    editor.putString("user_nombre", nombre)
                                    editor.putBoolean("is_logged", true)
                                    editor.apply()

                                    // 4. Redirigir según el rol obtenido de Firestore
                                    val intent = if (rol == "admin") {
                                        Intent(this, DashboardActivity::class.java)
                                    } else {
                                        Intent(this, HomeActivity::class.java)
                                    }

                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "El usuario no tiene un perfil registrado en la base de datos", Toast.LENGTH_LONG).show()
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error al obtener datos del usuario: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
    }
}