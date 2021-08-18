plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    android()
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 28
        targetSdk = 30
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    val compose_version = "1.0.1"

    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }

    dependencies {
        val accompanist_version = "0.13.0"

        implementation(project(":parser"))

        implementation("androidx.compose.runtime:runtime:$compose_version")
        implementation("androidx.compose.ui:ui:$compose_version")
        implementation("androidx.compose.ui:ui-tooling:$compose_version")
        implementation("androidx.compose.foundation:foundation-layout:$compose_version")
        implementation("androidx.compose.material:material:$compose_version")
        implementation("androidx.compose.material:material-icons-extended:$compose_version")
        implementation("androidx.compose.foundation:foundation:$compose_version")
        implementation("androidx.compose.animation:animation:$compose_version")
    }
}
