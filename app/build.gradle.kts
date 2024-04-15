plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ramri.MoviesChallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ramri.movies_challenge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity.ktx)
    implementation(libs.cardview)
    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    implementation(libs.datastore)
    implementation(libs.datastore.preferences)
    implementation(libs.drawerlayout)
    implementation(libs.fragment.ktx)
    implementation(libs.hilt.common)
    implementation(libs.hilt.work)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation.fragment)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.dynamic.features.fragment)
    implementation(libs.navigation.testing)
    implementation(libs.paging.runtime)
    implementation(libs.recyclerview)
    implementation(libs.recyclerview.selection)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.viewpager2)
    implementation(libs.work.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)

    debugImplementation(libs.fragment.testing)

    ksp(libs.lifecycle.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.paging.common)
    testImplementation(libs.room.testing)

    androidTestImplementation(libs.work.testing)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.ext.junit.ktx)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.test.runner)
}