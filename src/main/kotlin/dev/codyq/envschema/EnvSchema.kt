package dev.codyq.envschema

import dev.codyq.envschema.annotations.EnvIgnored
import dev.codyq.envschema.annotations.EnvName
import dev.codyq.envschema.annotations.EnvObject
import dev.codyq.envschema.exceptions.SerializerAlreadyExistsException
import dev.codyq.envschema.serializers.*
import dev.codyq.envschema.util.asSnakeCase
import java.util.UUID

object EnvSchema {

    private val envSerializers = mutableMapOf<Class<*>, EnvSerializer<*>>(
        Boolean::class.java to BooleanEnvSerializer,
        Byte::class.java to ByteEnvSerializer,
        Char::class.java to CharEnvSerializer,
        Double::class.java to DoubleEnvSerializer,
        Float::class.java to FloatEnvSerializer,
        Int::class.java to IntEnvSerializer,
        Long::class.java to LongEnvSerializer,
        Short::class.java to ShortEnvSerializer,
        String::class.java to StringEnvSerializer,
        UUID::class.java to UUIDEnvSerializer,
    )

    fun <T> load(obj: T, prefix: String = "", suffix: String = ""): T {
        val clazz = obj!!::class.java

        if (!clazz.isAnnotationPresent(EnvObject::class.java)) {
            return obj
        }

        val fields = clazz.declaredFields

        for (field in fields) {
            if (field.isAnnotationPresent(EnvIgnored::class.java)) {
                continue
            }

            val envName = field.getAnnotation(EnvName::class.java)?.value ?: field.name.asSnakeCase()

            field.isAccessible = true

            if (field.get(obj)::class.java.isAnnotationPresent(EnvObject::class.java)) {
                load(field.get(obj), prefix = "${prefix}${envName}_", suffix)
                continue
            }

            val envValue = System.getenv(prefix + envName + suffix) ?: continue

            val serializer = envSerializers[field.type]

            if (serializer == null) {
                println("Serializer for type ${field.type} not found.")
                continue
            }

            val resultingValue = serializer.serialize(envValue)
            field.set(obj, resultingValue)
        }

        return obj
    }

    fun <T> registerSerializer(clazz: Class<T>, serializer: EnvSerializer<T>) {
        if (envSerializers.containsKey(clazz)) {
            throw SerializerAlreadyExistsException(clazz)
        }
        envSerializers[clazz] = serializer
    }

    fun <T> unregisterSerializer(clazz: Class<T>) {
        envSerializers.remove(clazz)
    }

}
