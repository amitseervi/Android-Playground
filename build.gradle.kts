// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Comment
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.google.service) apply false
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.spotless)
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")
val ktLintVersion = "1.3.0"

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt")
        ktlint(ktLintVersion).editorConfigOverride(
            mapOf(
                "ktlint_code_style" to "android",
                "ij_kotlin_allow_trailing_comma" to true,
                "disabled_rules" to
                        "filename," +
                        "annotation,annotation-spacing," +
                        "argument-list-wrapping," +
                        "double-colon-spacing," +
                        "enum-entry-name-case," +
                        "multiline-if-else," +
                        "no-empty-first-line-in-method-block," +
                        "package-name," +
                        "trailing-comma," +
                        "spacing-around-angle-brackets," +
                        "spacing-between-declarations-with-annotations," +
                        "spacing-between-declarations-with-comments," +
                        "unary-op-spacing"
            )
        )
        licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
    }
    format("kts") {
        target("**/*.kts")
        targetExclude("**/build/**/*.kts")
        // Look for the first line that doesn't have a block comment (assumed to be the license)
        licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "(^(?![\\/ ]\\*).*$)")
    }
}