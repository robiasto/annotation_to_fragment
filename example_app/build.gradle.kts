import com.github.gradle.node.npm.task.NpmInstallTask
import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("io.freefair.lombok") version "8.4"
    id("application")
    id("com.github.node-gradle.node") version "3.5.1"
    id("org.sonarqube") version "5.0.0.4638"
    id("jacoco")
}

private object Versions {
    const val FLYWAY = "10.17.0"
    const val GUAVA = "31.0.1-jre"
    const val JAKARTA = "3.0.2"
    const val MOLECULES = "0.21.0"
    const val JAVA_FAKER = "1.0.2"
    const val MODULITH = "1.2.4"
    const val NODE = "20.17.0"
    const val NPM = "10.8.2"
    const val SPRING_BOOT = "3.3.4"
    const val SF_DEPENDENCIES = "3.3.1"
    const val SF_MODULITH_BOM = "1.2.0"
    const val JSOUP = "1.17.2"
    const val JAVA = "17"
    object WEBJARS {
         const val LOCATOR = "0.46"
         const val ALPINE = "3.11.1"
         const val SLIM_SELECT = "2.9.0"
    }
}

version = "1.0.SNAPSHOT"
group = "de.robiasto"

sonar {
    properties {
        property("sonar.projectKey", "annotation-view")
        property("sonar.projectName", "annotation-view")
        property("sonar.host.url", "http://localhost:9001")
        property("sonar.sources", "src/main")
        property("sonar.tests", "src/test")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Versions.JAVA))
    }
}

application {
    mainClass.set("de.robiasto.app.Application")
}

repositories {
    mavenCentral()
}

node {
    version = Versions.NODE
    npmVersion = Versions.NPM
    download = true
}

dependencies {
    runtimeOnly("org.flywaydb:flyway-core:${Versions.FLYWAY}")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:${Versions.FLYWAY}")

    implementation(project(":core"))
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-security:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_BOOT}")
    implementation("com.github.javafaker:javafaker:${Versions.JAVA_FAKER}") { exclude(group = "org.yaml", module = "snakeyaml") }
    implementation("org.springframework.boot:spring-boot-devtools:${Versions.SPRING_BOOT}")
    implementation("jakarta.validation:jakarta.validation-api:${Versions.JAKARTA}")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.springframework.boot:spring-boot-starter-validation:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-actuator:${Versions.SPRING_BOOT}")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("org.springframework.modulith:spring-modulith-starter-jpa:${Versions.MODULITH}")
    implementation("org.springframework.modulith:spring-modulith-actuator")
    implementation("org.springframework.modulith:spring-modulith-core:${Versions.MODULITH}")
    implementation("org.springframework.modulith:spring-modulith-starter-core:${Versions.MODULITH}")
    implementation("org.springframework.modulith:spring-modulith-docs:${Versions.MODULITH}")
    implementation("org.jmolecules.integrations:jmolecules-starter-ddd:${Versions.MOLECULES}")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.webjars.npm:alpinejs:${Versions.WEBJARS.ALPINE}")
    runtimeOnly("org.webjars.npm:slim-select:${Versions.WEBJARS.SLIM_SELECT}")
    runtimeOnly("org.webjars:webjars-locator:${Versions.WEBJARS.LOCATOR}")

    testImplementation("org.springframework.modulith:spring-modulith-test:${Versions.MODULITH}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jsoup:jsoup:${Versions.JSOUP}")
    testImplementation("org.hibernate.validator:hibernate-validator")
    testImplementation("org.springframework.security:spring-security-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.SF_DEPENDENCIES}")
        mavenBom("org.springframework.modulith:spring-modulith-bom:${Versions.SF_MODULITH_BOM}")
    }
}

tasks {
    register("installFrontendDependencies", NpmInstallTask::class) {
        description = "Install frontend dependencies using npm"
        group = "frontend"
    }
    register("buildFrontendProduction", NpmTask::class) {
        System.out.println("build")
        dependsOn("installFrontendDependencies")
        description = "Build frontend in production mode"
        group = "frontend"
        args = listOf("run", "build-prod")
    }
    withType<JacocoReport> {
        reports {
            xml.required.set(true)
            html.required.set(true)
            csv.required.set(false)
        }
    }
}

tasks.named<ProcessResources>("processResources") {
    exclude("**/*.html", "**/*.css")
    if( System.getenv("SPRING_PROFILES_ACTIVE") != "local"){
        finalizedBy("buildFrontendProduction")
    }
    if (gradle.startParameter.taskNames.isEmpty()) {
        println("Gradle Reload detected, skipping execution...")
    } else {
        println("Regular Gradle Build: ${gradle.startParameter.taskNames}")
    }
}




tasks.test {
    dependsOn(tasks.named("installFrontendDependencies"))
    dependsOn(tasks.named("buildFrontendProduction"))
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()
}
