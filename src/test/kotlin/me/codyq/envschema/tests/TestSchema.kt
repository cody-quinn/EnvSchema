package me.codyq.envschema.tests

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.system.withEnvironment
import io.kotest.matchers.shouldBe
import me.codyq.envschema.EnvSchema
import me.codyq.envschema.tests.schemas.PersonSchema
import java.util.UUID

class TestSchema : StringSpec({

    lateinit var schema: PersonSchema
    lateinit var prefixedSchema: PersonSchema
    lateinit var suffixedSchema: PersonSchema

    beforeEach {
        schema = EnvSchema.load(PersonSchema())
        prefixedSchema = EnvSchema.load(PersonSchema(), prefix = "PERSON_")
        suffixedSchema = EnvSchema.load(PersonSchema(), suffix = "_PERSON")
    }

    "name should be 'Johnny Appleseed'" {
        schema.name shouldBe "Johnny Appleseed"
    }

    "age should be changed to an integer 19" {
        schema.age shouldBe 19
    }

    "race should be 'White' from environment variable 'ETHNICITY'" {
        schema.race shouldBe "White"
    }

    "createdAt should be unaffected no matter if 'CREATED_AT' is set" {
        schema.createdAt shouldBe "2019/09/21 04:23 AM"
    }

    "name should be 'Bill Gates' on prefixedSchema" {
        prefixedSchema.name shouldBe "Bill Gates"
    }

    "name should be 'Steve Jobs' on suffixedSchema" {
        suffixedSchema.name shouldBe "Steve Jobs"
    }

    "sexuality gender name should be Female" {
        schema.sexuality.gender.name shouldBe "Female"
    }

    "sexuality orientation should be Homosexual" {
        schema.sexuality.orientation shouldBe "Homosexual"
    }

    "uuid should be '0500eb55-c461-4fca-8971-5012bcfff41b'" {
        schema.uuid shouldBe UUID.fromString("0500eb55-c461-4fca-8971-5012bcfff41b")
    }

})
