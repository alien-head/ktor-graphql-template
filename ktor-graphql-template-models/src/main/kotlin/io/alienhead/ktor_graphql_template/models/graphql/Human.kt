package io.alienhead.ktor_graphql_template.models.graphql

import com.expediagroup.graphql.generator.scalars.ID
import java.util.UUID

data class Human(
    var id: ID = ID(UUID.randomUUID().toString()),
    var firstName: String,
    var lastName: String,
) {
    constructor(input: HumanInput) : this(
        ID(UUID.randomUUID().toString()),
        input.firstName,
        input.lastName,
    )

    constructor(id: ID, input: HumanInput) : this(
        id,
        input.firstName,
        input.lastName,
    )

    constructor(id: UUID, input: HumanInput) : this(
        ID(id.toString()),
        input.firstName,
        input.lastName,
    )

    constructor(id: UUID, firstName: String, lastName: String) : this(
        ID(id.toString()),
        firstName,
        lastName,
    )
}

data class HumanInput(
    val firstName: String,
    val lastName: String,
)

data class HumansContainer(val humans: List<Human>)
data class createHumanContainer(val createHuman: Human)
