package me.codyq.envschema.tests.schemas

import me.codyq.envschema.annotations.EnvIgnored
import me.codyq.envschema.annotations.EnvName

class PersonSchema(
    val name: String,
    val age: String,
    @EnvName("ETHNICITY") val race: String,
    @EnvIgnored val createdAt: String,
)
