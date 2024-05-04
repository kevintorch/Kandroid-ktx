package com.kevintorch.androidktx

fun <E> MutableList<E>.appendAll(collection: Collection<E>): MutableList<E> {
    addAll(collection)
    return this
}
