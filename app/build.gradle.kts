plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.bookstore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bookstore"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.firebase:firebase-firestore:24.10.3")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.firebase:firebase-messaging:23.4.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("io.github.g00fy2.quickie:quickie-unbundled:1.9.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.google.zxing:core:3.3.0")
    implementation ("com.google.code.gson:gson:2.10.1")

    var lifecycle_version = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-extensions:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime:${lifecycle_version}")

    val koin_version = "3.3.0"
    val koin_core_version = "3.2.2"
    implementation ("io.insert-koin:koin-core:${koin_core_version}")
    implementation ("io.insert-koin:koin-android:${koin_version}")
    implementation ("io.insert-koin:koin-androidx-navigation:${koin_version}")
    implementation ("io.insert-koin:koin-androidx-compose:${koin_version}")

    // Room
    implementation("androidx.room:room-runtime:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("androidx.core:core-animation:1.0.0-beta01")

    //lottie
    val lottieVersion = "6.0.1"
    implementation ("com.airbnb.android:lottie:$lottieVersion")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")


    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")



}