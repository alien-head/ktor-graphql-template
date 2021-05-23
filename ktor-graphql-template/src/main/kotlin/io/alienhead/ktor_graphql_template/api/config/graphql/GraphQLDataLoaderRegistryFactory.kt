package io.alienhead.ktor_graphql_template.api.config.graphql

import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import org.dataloader.DataLoaderRegistry

class GraphQLDataLoaderRegistryFactory : DataLoaderRegistryFactory {

    override fun generate(): DataLoaderRegistry {
        val registry = DataLoaderRegistry()

        // Examples
//        registry.register(UniversityDataLoader.dataLoaderName, UniversityDataLoader.getDataLoader())
//        registry.register(CourseDataLoader.dataLoaderName, CourseDataLoader.getDataLoader())
//        registry.register(BookDataLoader.dataLoaderName, BookDataLoader.getDataLoader())
        return registry
    }
}
