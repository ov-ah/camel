plugins {
    id("fabric-loom")
    kotlin("jvm")
}

group = property("maven_group").toString()
version = property("mod_version").toString()

repositories {
    mavenCentral()
}

val imguiVersion = property("imgui_version").toString()

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")

    // ImGui
    listOf("imgui-java-binding", "imgui-java-lwjgl3").forEach { artifact ->
        implementation("io.github.spair:$artifact:$imguiVersion")
        include("io.github.spair:$artifact:$imguiVersion")
    }
    listOf("imgui-java-natives-linux", "imgui-java-natives-windows", "imgui-java-natives-macos").forEach { artifact ->
        runtimeOnly("io.github.spair:$artifact:$imguiVersion")
        include("io.github.spair:$artifact:$imguiVersion")
    }
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
}

kotlin {
    jvmToolchain(21)
}
