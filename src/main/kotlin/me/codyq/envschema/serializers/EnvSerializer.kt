package me.codyq.envschema.serializers

interface EnvSerializer<T> {

    fun serialize(input: String): T

}
