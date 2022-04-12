package me.codyq.envschema.tests

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.system.withEnvironment
import io.kotest.matchers.shouldBe
import me.codyq.envschema.EnvSchema
import me.codyq.envschema.tests.schemas.PersonSchema

class TestSchema : StringSpec({

    lateinit var schema: PersonSchema

    beforeEach {
        val default = PersonSchema("Billy Jones", "17", "Native American", "2019/09/21 04:23 AM")
        schema = EnvSchema.load(default)
    }

    "name should be 'Johnny Appleseed'" {
        schema.name shouldBe "Johnny Appleseed"
    }

    "age should be unchanged from the default" {
        schema.age shouldBe "17"
    }

    "race should be 'White' from environment variable 'ETHNICITY'" {
        schema.race shouldBe "White"
    }

    "createdAt should be unaffected no matter if 'CREATED_AT' is set" {
        schema.createdAt shouldBe "2019/09/21 04:23 AM"
    }

})
