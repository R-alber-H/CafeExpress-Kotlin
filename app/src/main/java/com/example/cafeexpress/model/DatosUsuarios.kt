package com.example.cafeexpress.model

class DatosUsuarios {

    private val listaUsuarios = listOf(
        User("admin@gmail.com", "123456", "admin", nombre = "Jaime Paredes"),
        User("cliente@gmail.com", "123456", "cliente", nombre = "Jhon Perez")
    )

    fun obtenerUsuarios(): List<User> {
        return listaUsuarios
    }
}