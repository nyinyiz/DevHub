import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

fun loadLocalProperties(project: Project): Properties {
    val properties = Properties()
    val localPropertiesFile =
        File(project.rootProject.file("local.properties").absolutePath)
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    } else {
        println("local.properties file not found.")
    }
    return properties
}

android {
    namespace = "com.nyinyi.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            val localProperties = loadLocalProperties(project)
            val authToken =
                localProperties.getProperty("auth-token") ?: "your_api_key_here"
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "AUTH_TOKEN", "\"$authToken\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            val localProperties = loadLocalProperties(project)
            val authToken =
                localProperties.getProperty("auth-token") ?: "your_api_key_here"
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "AUTH_TOKEN", "\"$authToken\"")
            isMinifyEnabled = false
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain-model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // KotlinX
    implementation(libs.kotlinx.serialization)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Retrofit
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.retrofit.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // OkHttp
    api(libs.okhttp)

    // Inject
    implementation(libs.inject)
}