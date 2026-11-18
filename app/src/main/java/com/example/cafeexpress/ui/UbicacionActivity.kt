
package com.example.cafeexpress.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeexpress.R

import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.maplibre.android.annotations.MarkerOptions

class UbicacionActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar MapLibre antes de crear el mapa
        MapLibre.getInstance(this)

        setContentView(R.layout.activity_ubicacion)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        val btnRegresar = findViewById<ImageView>(R.id.btnRegresar)

        // Coordenadas de la ubicación simulada
        val latitud = -12.116319181337653
        val longitud = -77.02406190337297

        val ubicacion = LatLng(latitud, longitud)

        // Cargar mapa gratuito de OpenFreeMap
        mapView.getMapAsync { map ->

            map.setStyle(
                "https://tiles.openfreemap.org/styles/liberty"
            ) { style ->

                // Centrar el mapa en la ubicación
                map.cameraPosition = CameraPosition.Builder()
                    .target(ubicacion)
                    .zoom(16.0)
                    .build()

                // Agregar marcador
                map.addMarker(
                    MarkerOptions()
                        .position(ubicacion)
                        .title("CaféExpress - Sucursal Principal")
                        .snippet("¡Aquí nos encuentras!")
                )
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mapView.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
}
