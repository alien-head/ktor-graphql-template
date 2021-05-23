package io.alienhead.ktor_graphql_template.api.schema.human

import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.scalars.ID
import io.alienhead.ktor_graphql_template.api.utils.asUuid
import io.alienhead.ktor_graphql_template.models.graphql.Human
import io.alienhead.ktor_graphql_template.models.graphql.HumanInput

class HumanSchema {
    class HumanQuery {
        suspend fun humans() = HumanRepository.selectAll()
        suspend fun human(id: ID) = HumanRepository.selectById(id.asUuid())
    }

    class HumanMutation {
        suspend fun createHuman(human: HumanInput): Human {
            val humanToAdd = Human(human)
            HumanRepository.insert(humanToAdd)

            return humanToAdd
        }

        suspend fun updateHuman(id: ID, request: HumanInput): Human? {
            val found = HumanRepository.selectById(id.asUuid())

            return if (found == null) {
                null
            } else {
                val human = Human(id, request)
                HumanRepository.update(human)
                human
            }
        }

        suspend fun deleteHuman(id: ID): String {
            HumanRepository.delete(id.asUuid())
            return "Deleted Human $id"
        }
    }

    companion object {
        fun queries() = TopLevelObject(HumanQuery())
        fun mutations() = TopLevelObject(HumanMutation())
    }
}
