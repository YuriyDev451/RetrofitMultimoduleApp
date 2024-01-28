pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RetrofitMultimoduleApp"
include(":app")
include(":features")
include(":entities")
include(":domain")
include(":data")
include(":network")
include(":common")
include(":features:account")
include(":features:flights")
include(":features:searchdetail")
