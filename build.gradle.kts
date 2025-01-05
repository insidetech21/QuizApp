// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.2" apply false

    id("com.android.library") version "8.1.4" apply false
    kotlin("android") version "1.9.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add this line to include the Google services classpath
        classpath("com.google.gms:google-services:4.4.2")
        // If you use Firebase, include the Firebase plugin as well.
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
    }
}
