package me.codyq.envschema.serializers

object BooleanEnvSerializer : EnvSerializer<Boolean> {
    override fun serialize(input: String): Boolean = input.toBoolean()
}

object ByteEnvSerializer : EnvSerializer<Byte> {
    override fun serialize(input: String): Byte = input.toByte()
}

object CharEnvSerializer : EnvSerializer<Char> {
    override fun serialize(input: String): Char = input.toInt().toChar()
}

object DoubleEnvSerializer : EnvSerializer<Double> {
    override fun serialize(input: String): Double = input.toDouble()
}

object FloatEnvSerializer : EnvSerializer<Float> {
    override fun serialize(input: String): Float = input.toFloat()
}

object IntEnvSerializer : EnvSerializer<Int> {
    override fun serialize(input: String): Int = input.toInt()
}

object LongEnvSerializer : EnvSerializer<Long> {
    override fun serialize(input: String): Long = input.toLong()
}

object ShortEnvSerializer : EnvSerializer<Short> {
    override fun serialize(input: String): Short = input.toShort()
}
