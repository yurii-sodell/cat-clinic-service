plugins {
    id("application")
}

group = "be.kdg.pro3"
version = "1.0-SNAPSHOT"

application {
    mainClass = "be.kdg.pro3.catclinic.CatClinicApp"
}



repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run",JavaExec::class){
    standardInput=System.`in`
}