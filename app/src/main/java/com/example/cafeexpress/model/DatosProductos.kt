package com.example.cafeexpress.model

import com.example.cafeexpress.R

class DatosProductos {
    fun obtenerProductosPopulares(): List<Producto> {
        return listOf(
            Producto("Café Capuchino", "$4.50", R.drawable.cafe1),
            Producto("Café Latte", "$3.80", R.drawable.cafe1),
            Producto("Espresso", "$2.50", R.drawable.cafe1),
            Producto("Moka Helado", "$5.00", R.drawable.cafe1),
            Producto("Café Americano", "$3.00", R.drawable.cafe1)
        )
    }
}