// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.12.0"
}

group = "org.intellij.sdk"
version = "1.0.0"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
  version.set("2022.1.4")
  type.add("IC")
  type.add("IU")
  type.add("CL")
  type.add("PS")
  plugins.set(listOf("com.jetbrains.php:221.5787.21"))
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("221")
    untilBuild.set("223.*")
  }
}

dependencies{
  // https://mvnrepository.com/artifact/org.eclipse.mylyn.github/org.eclipse.egit.github.core
  implementation ("org.eclipse.mylyn.github:org.eclipse.egit.github.core:2.1.5")

}
