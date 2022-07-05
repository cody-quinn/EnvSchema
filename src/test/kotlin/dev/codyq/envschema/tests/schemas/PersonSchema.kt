package dev.codyq.envschema.tests.schemas

import dev.codyq.envschema.annotations.EnvIgnored
import dev.codyq.envschema.annotations.EnvName
import dev.codyq.envschema.annotations.EnvObject
import java.util.UUID

@EnvObject
data class PersonSchema(
    val uuid: UUID = UUID.fromString("05205fdf-7e2c-44ae-a9eb-dbf6ed6389da"),
    val name: String = "Billy Jones",
    val age: Int = 17,
    val sexuality: PersonSexuality = PersonSexuality(),
    @EnvName("ETHNICITY") val race: String = "Native American",
    @EnvIgnored val createdAt: String = "2019/09/21 04:23 AM",
)

@EnvObject
data class PersonSexuality(
    val gender: PersonSexualityGender = PersonSexualityGender(),
    val orientation: String = "Heterosexual",
)

@EnvObject
data class PersonSexualityGender(
    val name: String = "Male",
    val pronouns: String = "He/Him",
)
