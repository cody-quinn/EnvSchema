plugins {
    kotlin("jvm") version "1.6.10"

    `maven-publish`
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
    environment["UUID"] = "0500eb55-c461-4fca-8971-5012bcfff41b"
    environment["NAME"] = "Johnny Appleseed"
    environment["AGE"] = "19"
    environment["ETHNICITY"] = "White"
    environment["CREATED_AT"] = "Random datetime"
    
    environment["SEXUALITY_ORIENTATION"] = "Homosexual"
    environment["SEXUALITY_GENDER_NAME"] = "Female"

    environment["PERSON_NAME"] = "Bill Gates"
    environment["NAME_PERSON"] = "Steve Jobs"

    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["kotlin"])
        }
    }
}
