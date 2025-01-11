private object Versions {
    const  val SPRING_BOOT = "3.3.4"
    const val SF_DATA_JPA = "3.3.4"
    const val SF_DEPENDENCIES = "3.3.1"
    const val SF_MODULITH_BOM = "1.2.0"
    const val JSOUP = "1.17.2"
    const val JAVA = "17"
}


plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("io.freefair.lombok") version "8.10.2"
}

version = "1.0.SNAPSHOT"
group = "de.robiasto"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Versions.JAVA))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Versions.SF_DATA_JPA}")
    implementation("org.springframework.boot:spring-boot-starter-security:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_BOOT}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jsoup:jsoup:${Versions.JSOUP}")

    // Test Dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.SF_DEPENDENCIES}")
        mavenBom("org.springframework.modulith:spring-modulith-bom:${Versions.SF_MODULITH_BOM}")
    }
}
tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    register("cleanOut", Delete::class) {
        delete("$projectDir/out")
    }

    clean {
        dependsOn("cleanOut")
    }
}
