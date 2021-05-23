package io.alienhead.ktor_graphql_template_functional_tests.utils

import com.expediagroup.graphql.generator.scalars.ID
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonIdDeserializer : JsonDeserializer<ID> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ID {
        return ID(json!!.asString)
    }
}

fun Any.toJson(): String {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(ID::class.java, GsonIdDeserializer())

    val gson = gsonBuilder.create()
    return gson.toJson(this)
}

fun <T> Any?.toModel(clazz: Class<T>): T {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(ID::class.java, GsonIdDeserializer())

    val gson = gsonBuilder.create()
    val json = gson.toJson(this)
    return gson.fromJson(json, clazz)
}
