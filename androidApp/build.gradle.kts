plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("io.insert-koin:koin-android:3.4.0")
            }
        }
    }
}

android {
    namespace = "com.scanner.binpicking.android"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.scanner.binpicking.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.preview)
    implementation(compose.uiTooling)

    implementation("com.airbnb.android:lottie-compose:5.2.0")
    implementation("io.ktor:ktor-client-okhttp:2.3.1")
    implementation("com.arkivanov.decompose:decompose:2.0.0-compose-experimental-alpha-02")
    implementation("io.github.xxfast:decompose-router:0.2.1")

    implementation("androidx.camera:camera-camera2:1.2.2")
    implementation("androidx.camera:camera-lifecycle:1.2.2")
    implementation("androidx.camera:camera-view:1.2.2")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.maps.android:maps-compose:2.11.2")
    implementation("com.google.accompanist:accompanist-permissions:0.29.2-rc")
}