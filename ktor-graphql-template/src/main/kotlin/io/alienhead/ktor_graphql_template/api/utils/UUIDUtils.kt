package io.alienhead.ktor_graphql_template.api.utils

import com.expediagroup.graphql.generator.scalars.ID
import java.util.UUID

fun ID.asUuid(): UUID = UUID.fromString(this.value)
