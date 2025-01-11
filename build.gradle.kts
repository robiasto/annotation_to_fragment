
plugins {
    id("org.sonarqube") version "5.0.0.4638"
    id("jacoco")
}
sonar {
    properties {
        property("sonar.projectKey", "all")
        property("sonar.projectName", "all")
        property("sonar.host.url", "http://localhost:9001")
        property("sonar.sources", "**/src/main")
        property("sonar.tests", "**/src/test")
    }
}
version = "1.0.SNAPSHOT"
group = "de.robiasto"



tasks {
    withType<JacocoReport> {
        reports {
            xml.required.set(true)
            html.required.set(true)
            csv.required.set(false)
        }
    }
}
