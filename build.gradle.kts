repositories {
  mavenCentral()
  maven("https://jitpack.io")
}
plugins {
  kotlin("jvm") version "1.5.30"
  kotlin("plugin.serialization") version "1.5.30"
  id("com.github.johnrengelman.shadow") version "7.0.0"
}
dependencies {
  implementation("com.googlecode.ez-vcard:ez-vcard:0.11.2")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
  implementation("org.redisson:redisson:3.16.2")
  implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.5")
  implementation("ch.qos.logback:logback-classic:1.2.5")
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.0-RC1")
  testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.24")
  testImplementation("io.mockk:mockk:1.12.0")
}
tasks.compileKotlin {
  kotlinOptions.jvmTarget = "16"
  kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime"
}
tasks.compileTestKotlin {
  kotlinOptions.jvmTarget = "16"
  kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime"
}
tasks.test {
  useJUnitPlatform()
}
tasks.jar {
  isZip64 = true
  manifest.attributes("Main-Class" to "AppKt")
}
tasks.shadowJar {
  minimize()
}
tasks.build {
  dependsOn(tasks.shadowJar)
}
