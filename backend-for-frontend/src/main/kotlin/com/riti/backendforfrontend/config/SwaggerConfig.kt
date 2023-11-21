package com.riti.backendforfrontend.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import lombok.RequiredArgsConstructor
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * http://localhost:8080/webjars/swagger-ui/index.html
 */
@OpenAPIDefinition(info = Info(title = "Dating App", description = "Dating-be api명세", version = "v1"))
@RequiredArgsConstructor
@Configuration
class SwaggerConfig {
    @Bean
    fun chatOpenApi(): GroupedOpenApi? {
        val paths = arrayOf("/**")
        return GroupedOpenApi.builder()
                .group("COUPLE API v1")
                .pathsToMatch(*paths)
                .build()
    }
}