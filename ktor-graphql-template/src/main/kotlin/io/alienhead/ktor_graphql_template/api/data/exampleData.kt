package io.alienhead.ktor_graphql_template.api.data

import io.alienhead.ktor_graphql_template.api.config.db.DatabaseFactory.suspendedTransaction
import io.alienhead.ktor_graphql_template.api.schema.human.HumanRepository
import io.alienhead.ktor_graphql_template.models.graphql.Human
import java.util.UUID

suspend fun mockData() {
    suspendedTransaction {
        HumanRepository.insert(Human(UUID.fromString("eafb0e9b-309a-402b-bef7-c04b1cb58582"), "Charlie", "Kelly"))
        HumanRepository.insert(Human(UUID.fromString("21b10433-9a37-439d-868f-7d0b690f7864"), "Ronald", "MacDonald"))
        HumanRepository.insert(Human(UUID.fromString("3dd9f0c6-0c43-44ab-a4ac-c5ffe704ada7"), "Dennis", "Reynolds"))
        HumanRepository.insert(Human(UUID.fromString("3e1905e4-9607-4987-9d56-816d5335918c"), "Dee", "Reynolds"))
        HumanRepository.insert(Human(UUID.fromString("3e1905e4-9607-4987-9d56-816d5335918d"), "Frank", "Reynolds"))
    }
}
