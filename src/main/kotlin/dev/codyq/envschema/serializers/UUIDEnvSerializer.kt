package dev.codyq.envschema.serializers

import java.util.*

object UUIDEnvSerializer : EnvSerializer<UUID> {
    override fun serialize(input: String): UUID = UUID.fromString(input)
}
