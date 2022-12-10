package com.example.abbtechgraduationproject.utils


fun <T> List<T>?.toSafeList(): List<T> {
    return if (this.isNullOrEmpty() || this[0] == "") emptyList()
    else this
}

fun <T> Collection<T>?.toSafeCollections(): Collection<T> {
    return if (this.isNullOrEmpty()) emptyList()
    else this
}

fun <T> Set<T>?.toSafeSet(): Set<T> {
    return if (this.isNullOrEmpty()) emptySet()
    else this
}

fun <T> T?.or(default: T): T = this ?: default
