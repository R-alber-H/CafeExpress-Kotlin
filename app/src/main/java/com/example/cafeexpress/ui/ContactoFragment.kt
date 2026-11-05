package com.example.cafeexpress.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cafeexpress.R

class ContactoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacto, container, false)

        val etNombreContacto = view.findViewById<EditText>(R.id.etNombreContacto)
        val etApellidoContacto = view.findViewById<EditText>(R.id.etApellidoContacto)
        val etTelefonoContacto = view.findViewById<EditText>(R.id.etTelefonoContacto)
        val btnAgregar = view.findViewById<Button>(R.id.btnAgregarContacto)

        val txtNombreRes = view.findViewById<TextView>(R.id.txtNombreResultado)
        val txtTelefonoRes = view.findViewById<TextView>(R.id.txtTelefonoResultado)

        btnAgregar.setOnClickListener {
            val nombre = etNombreContacto.text.toString().trim()
            val apellido = etApellidoContacto.text.toString().trim()
            val telefono = etTelefonoContacto.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            txtNombreRes.text = "$nombre $apellido"
            txtTelefonoRes.text = telefono

            Toast.makeText(requireContext(), "Contacto añadido con éxito", Toast.LENGTH_SHORT).show()


            etNombreContacto.setText("")
            etApellidoContacto.setText("")
            etTelefonoContacto.setText("")
            etNombreContacto.requestFocus()
        }

        return view
    }
}