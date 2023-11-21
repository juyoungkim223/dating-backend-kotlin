package com.riti.backendforfrontend.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.r2dbc.core.DatabaseClient


@Configuration
class R2dbcMySQLConfig {
        @Bean
        fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
                val initializer = ConnectionFactoryInitializer()
                initializer.setConnectionFactory(connectionFactory)
                initializer.setDatabasePopulator(
                        // r2dbc ddl mode does not support
                        ResourceDatabasePopulator(
                                ClassPathResource("createSchema.sql")
                        )
                )
                return initializer
        }


}