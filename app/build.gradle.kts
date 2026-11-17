plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.cafeexpress"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.cafeexpress"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    // Firebase BoM (Bill of Materials) para controlar versiones compatibles
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))

    // Firebase Authentication (para crear cuentas y hacer login)
    implementation("com.google.firebase:firebase-auth-ktx")

    // Cloud Firestore (para guardar los datos del usuario y su rol)
    implementation("com.google.firebase:firebase-firestore-ktx")
}