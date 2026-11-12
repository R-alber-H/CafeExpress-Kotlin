// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // Agrega esta línea aquí abajo:
    id("com.google.gms.google-services") version "4.5.0" apply false
}