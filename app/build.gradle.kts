import org.gradle.kotlin.dsl.execution.Program
plugins {
    id(Pluggins.androidApplication)
    id(Pluggins.jetbrainsKotlin)
    id(Pluggins.kotlinKapt)
    id(Pluggins.hilt)
}

android {
    namespace = "com.yuriyyg.retrofitmultimoduleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yuriyyg.retrofitmultimoduleapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {


    implementation(project(":features:account"))
    implementation(project(":features:flights"))



    implementation(Libs.UI.material)



    implementation(Libs.UI.material)

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.hiltKapt)
}