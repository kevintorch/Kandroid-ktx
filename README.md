# Kandroid Kotlin Extensions

Common Kotlin extensions to used in Android projects.

Latest Version 👉  [![](https://jitpack.io/v/kevintorch/Kandroid-ktx.svg)](https://jitpack.io/#kevintorch/Kandroid-ktx)

### Installation

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency

```groovy
dependencies {
    implementation "com.github.kevintorch:Kandroid-ktx:$latest_version"
}
```

### Usage
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // animation
        someView.animate {
            nestedView.isVisible = true
        }

        // individual padding with **dp** extension (return Int in view-based system)
        someView.padding(top = 16.dp)
        someView.padding(vertical = 24.dp)

        
    }
}
```
