package dev.codyq.envschema.util

internal fun String.asSnakeCase() =
    Regex("^[a-z]+|[A-Z][a-z]+")
        .findAll(this)
        .map { it.value }
        .joinToString("_")
        .uppercase()
