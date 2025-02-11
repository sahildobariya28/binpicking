plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
}


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export("com.arkivanov.decompose:decompose:<version>")
            export("com.arkivanov.essenty:lifecycle:<essenty_version>")
        }
    }

    sourceSets {
        commonMain.dependencies {

            //compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            //kotlinx
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            //ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            //imageLoader
            implementation(libs.image.loader)
            implementation(libs.kamel.image)

            //logger
            implementation(libs.napier)

            //mvvm
            implementation(libs.mvvm.core)

            //decompose
            implementation(libs.decompose)
            implementation(libs.decompose.jetbrains)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.collections.immutable)

            implementation("co.touchlab.crashkios:crashlytics:0.8.6")

            implementation("com.stevdza-san:messagebarkmp:1.0.5")
//            implementation("network.chaintech:cmptoast:1.0.1")
        }
        androidMain.dependencies {
            api(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            //decompose
            implementation(libs.decompose)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.decompose)
        }
    }
}

android {
    namespace = "com.scanner.binpicking"
    compileSdk = 34

    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.foundation.layout.android)
    commonMainApi(libs.mvvm.compose) // api mvvm-core, getViewModel for Compose Multiplatform
    commonMainApi(libs.mvvm.flow.compose) // api mvvm-flow, binding extensions for Compose Multiplatform
    commonMainApi(libs.mvvm.livedata.compose) // api mvvm-livedata, binding extensions for Compose Multiplatform
}