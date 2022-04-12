package me.codyq.envschema.exceptions

class SerializerAlreadyExistsException(clazz: Class<*>) : Exception("Serializer for class ${clazz.name} already registered")
