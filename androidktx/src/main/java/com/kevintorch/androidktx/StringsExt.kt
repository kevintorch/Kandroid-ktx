package com.kevintorch.androidktx

fun String.findContentDispositionValue(name: String): String? {
    val regex = """$name\s*=\s*"(.+?)"""".toRegex()
    val match = regex.find(this)
    return match?.groupValues?.get(1)
}