package me.codyq.envschema.tests.schemas

import me.codyq.envschema.annotations.EnvIgnored
import me.codyq.envschema.annotations.EnvName

class PersonSchema(
    val name: String = "Billy Jones",
    val age: String = "17",
    @EnvName("ETHNICITY") val race: String = "Native American",
    @EnvIgnored val createdAt: String = "2019/09/21 04:23 AM",
)
