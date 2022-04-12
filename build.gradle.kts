plugins {
    kotlin("jvm") version "1.6.10"
}

group = "me.codyq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

    testImplementation("io.kotest:kotest-runner-junit5:5.2.3")
}

tasks.withType<Test>().configureEach {
    environment["NAME"] = "Johnny Appleseed"
    environment["ETHNICITY"] = "White"
    environment["CREATED_AT"] = "Random datetime"

    useJUnitPlatform()
}
