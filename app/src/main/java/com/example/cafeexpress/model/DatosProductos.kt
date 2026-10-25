package com.example.cafeexpress.model

import com.example.cafeexpress.R

class DatosProductos {

    private val listaProductos = listOf(
        Producto("Americano Intenso", "S/14.50", R.drawable.cafe5),
        Producto("Caramel Macchiato", "S/23.80", R.drawable.cafe2),
        Producto("Croissant Almendras", "S/12.50", R.drawable.cafe3),
        Producto("Cold Brew Vainilla", "S/25.00", R.drawable.cafe6),
        Producto("Café Americano", "S/13.00", R.drawable.cafe5)
    )

    fun obtenerProductosPopulares(): List<Producto> {
        return listaProductos
    }
}