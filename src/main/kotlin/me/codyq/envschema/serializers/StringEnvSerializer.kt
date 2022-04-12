package me.codyq.envschema.serializers

object StringEnvSerializer : EnvSerializer<String> {
    override fun serialize(input: String): String = input
}
