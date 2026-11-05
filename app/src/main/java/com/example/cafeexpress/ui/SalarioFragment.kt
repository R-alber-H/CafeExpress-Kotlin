package com.example.cafeexpress.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.cafeexpress.R
import com.google.android.material.card.MaterialCardView
import java.util.*

class SalarioFragment : Fragment() {
    private lateinit var etFecha: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_salario, container, false)

        val cardFormulario = view.findViewById<MaterialCardView>(R.id.cardFormulario)
        val cardResultado = view.findViewById<MaterialCardView>(R.id.cardResultado)

        val etNombres = view.findViewById<EditText>(R.id.etNombres)
        val etApellidos = view.findViewById<EditText>(R.id.etApellidos)
        val spinnerCargo = view.findViewById<Spinner>(R.id.spinnerCargo)
        val etHoras = view.findViewById<EditText>(R.id.etHoras)
        etFecha = view.findViewById<EditText>(R.id.etFecha)
        val rgTurno = view.findViewById<RadioGroup>(R.id.rgTurno)

        val btnCalcular = view.findViewById<Button>(R.id.btnCalcular)
        val btnNuevoCalculo = view.findViewById<Button>(R.id.btnNuevoCalculo)

        val txtResultadoNombre = view.findViewById<TextView>(R.id.txtResultadoNombre)
        val txtResultadoCargo = view.findViewById<TextView>(R.id.txtResultadoCargo)
        val txtResultadoFecha = view.findViewById<TextView>(R.id.txtResultadoFecha)
        val txtResultadoTotal = view.findViewById<TextView>(R.id.txtResultadoTotal)

        val cargos = listOf("Seleccione un cargo", "Administrador", "Cajero", "Repartidor")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, cargos)
        spinnerCargo.adapter = adapter

        configurarCalendario()

        btnCalcular.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val cargoSeleccionado = spinnerCargo.selectedItem.toString()
            val horasString = etHoras.text.toString().trim()
            val fecha = etFecha.text.toString().trim()

            if (cargoSeleccionado == "Seleccione un cargo") {
                Toast.makeText(requireContext(), "Por favor, selecciona un cargo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nombres.isEmpty() || apellidos.isEmpty() || horasString.isEmpty() || fecha.isEmpty() || rgTurno.checkedRadioButtonId == -1) {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val horasTrabajadas = horasString.toDouble()

            val tarifaPorHora = when (cargoSeleccionado) {
                "Administrador" -> 12.0
                "Cajero" -> 10.0
                "Repartidor" -> 9.0
                else -> 10.0
            }

            val idTurnoSeleccionado = rgTurno.checkedRadioButtonId
            val turnoStr: String
            var bonoTurno = 0.0

            if (idTurnoSeleccionado == R.id.rbManana) {
                turnoStr = "Mañana"
            } else {
                turnoStr = "Tarde"
                bonoTurno = 2.0
            }

            val salarioTotal = horasTrabajadas * (tarifaPorHora + bonoTurno)

            txtResultadoNombre.text = "Empleado: $nombres $apellidos"
            txtResultadoCargo.text = "Cargo: $cargoSeleccionado | Turno: $turnoStr"
            txtResultadoFecha.text = "Fecha de pago: $fecha"
            txtResultadoTotal.text = "Salario Total: $%.2f".format(salarioTotal)

            cardFormulario.visibility = View.GONE
            cardResultado.visibility = View.VISIBLE
        }

        btnNuevoCalculo.setOnClickListener {
            etNombres.setText("")
            etApellidos.setText("")
            spinnerCargo.setSelection(0)
            etHoras.setText("")
            etFecha.setText("")
            rgTurno.clearCheck()

            cardResultado.visibility = View.GONE
            cardFormulario.visibility = View.VISIBLE
        }

        return view
    }

    private fun configurarCalendario() {
        etFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val anio = calendar.get(Calendar.YEAR)
            val mes = calendar.get(Calendar.MONTH)
            val dia = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val mesReal = month + 1
                    val fechaFormateada = String.format("%02d/%02d/%d", dayOfMonth, mesReal, year)
                    etFecha.setText(fechaFormateada)
                },
                anio, mes, dia
            )
            datePickerDialog.show()
        }
    }
}