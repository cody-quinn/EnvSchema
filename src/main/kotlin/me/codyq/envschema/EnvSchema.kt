package me.codyq.envschema

import me.codyq.envschema.annotations.EnvIgnored
import me.codyq.envschema.annotations.EnvName
import me.codyq.envschema.util.asSnakeCase

object EnvSchema {

    fun <T> load(obj: T): T {
        obj!!::class.java.declaredFields
            .filter { !it.isAnnotationPresent(EnvIgnored::class.java) }
            .forEach {
                val envName = it.getAnnotation(EnvName::class.java)?.value ?: it.name.asSnakeCase()
                val envValue = System.getenv(envName) ?: return@forEach

                it.isAccessible = true
                it.set(obj, envValue)
            }
        return obj
    }

}
