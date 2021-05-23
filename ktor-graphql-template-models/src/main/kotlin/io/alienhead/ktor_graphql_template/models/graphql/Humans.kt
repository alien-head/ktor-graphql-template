package io.alienhead.ktor_graphql_template.models.graphql

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Humans : Table() {
    val id = uuid("id")
    val firstName = varchar("first_name", 16)
    val lastName = varchar("last_name", 16)

    override val primaryKey = PrimaryKey(id)
}

fun ResultRow.toHuman() =
    Human(
        this[Humans.id],
        this[Humans.firstName],
        this[Humans.lastName],
    )

fun List<ResultRow>.toHumans() =
    this.map {
        it.toHuman()
    }
