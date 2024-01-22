// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Pluggins.androidApplication) version Versions.androidApplication apply false
    id(Pluggins.jetbrainsKotlin) version Versions.jetbrainsKotlin apply false
    id(Classpath.hilt) version Versions.hilt apply false
}