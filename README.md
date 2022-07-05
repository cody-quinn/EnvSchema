# EnvSchema

An advanced environment variable parsing library for Kotlin. 

### Features

###### Supported features

- Parsing of objects
- Parsing of nested objects
- Custom deserializers

###### Planned features

- Parsing enums & lists
- Returning and manipulating a modified clone of the object, instead of manipulating the current one

### Gradle Dependency

```
repositories {
    maven("https://repo.codyq.dev/public/maven")
}

dependencies {
    implementation("dev.codyq:EnvSchema:1.0.1")
}
```

### Example usage

Using the following schema & code as an example

```kotlin
import java.util.UUID

@EnvObject
data class Person(
    val uuid: UUID,
    val name: String,
    val age: Int,
    val sexuality: PersonSexuality,
    @EnvName("ETHNICITY") val race: String,
    @EnvIgnored val createdAt: String,
)

@EnvObject
data class PersonSexuality(
    val gender: String,
    val orientation: String,
)

fun main() {
    val currentPerson = Person(
        uuid = UUID.randomUUID(),
        name = "Johnny Appleseed",
        age = 24,
        sexuality = PersonSexuality(
            gender = "Male",
            orientation = "Heterosexual",
        ),
        race = "White",
        createdAt = "2022/11/23 09:27 PM",
    )
    
    // This will modify things even if they are final. The values on the object
    // passed in can be treated as their defaults.
    EnvSchema.load(currentPerson)
    
    println(currentPerson)
}
```

We can easily use the follow environment variables to modify the object

```properties
# We can set top level stuff easily
NAME="Jimmy"

# We can set nested objects by adding a '_' in between layers
SEXUALITY_GENDER="Female"

# We can change 'race' by using it's explicitly specified 'EnvName'
ETHNICITY="Asian"

# We can't change 'createdAt' no matter how hard we try
CREATED_AT="this won't work ;("

# More than just strings can be deserialized
UUID=61b19ab9-7df1-49e2-9d60-379db04f5c4f
```

### License

This project is open source under the MIT license.
