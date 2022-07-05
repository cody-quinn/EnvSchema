import java.net.URI

val mavenRepoUrl = System.getenv("MAVEN_REPO_URL") ?: "https://repo.codyq.dev/public/maven"
val mavenUsername = System.getenv("MAVEN_REPO_USERNAME") ?: properties["codyqMavenUsername"] as String?
val mavenPassword = System.getenv("MAVEN_REPO_PASSWORD") ?: properties["codyqMavenPassword"] as String?

val mavenCredentials: PasswordCredentials.() -> Unit = {
    username = mavenUsername
    password = mavenPassword
}

plugins {
    kotlin("jvm") version "1.6.20"
    `maven-publish`
    `java-library`
}

group = "dev.codyq"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    testImplementation("io.kotest:kotest-runner-junit5:5.3.1")
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

java {
    withSourcesJar()
    withJavadocJar()
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
    repositories {
        maven {
            url = URI.create(mavenRepoUrl)
            credentials(mavenCredentials)
        }
    }
}
