plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "comp2000hk.cw2.seasiderestaurant"
    compileSdk = 34

    defaultConfig {
        applicationId = "comp2000hk.cw2.seasiderestaurant"
        minSdk = 29
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation(platform("com.google.firebase:firebase-bom:32.7.1")) // Import the Firebase BoM
    implementation("com.google.firebase:firebase-analytics")            // Add the dependency for the Firebase SDK for Google Analytics
    implementation("com.google.firebase:firebase-auth")                 // Add the dependency for the Firebase Authentication library
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-inappmessaging:20.4.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")

    // Glide library
    implementation ("com.github.bumptech.glide:glide:4.6.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.6.1")
    // Volley library
    implementation("com.android.volley:volley:1.2.1")

    implementation("com.google.code.gson:gson:2.10")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Recyclerview Library
    implementation("androidx.recyclerview:recyclerview:1.2.1")

}