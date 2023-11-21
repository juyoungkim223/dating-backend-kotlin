package com.riti.backendforfrontend.config

import com.riti.backendforfrontend.handler.MessageSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

/**
 * WebSocket config
 */
@Configuration
class WebSocketConfig (
    val messageSocketHandler: MessageSocketHandler
) {
    @Bean
    fun websocketHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun handlerMapping() : HandlerMapping {
        val handlerMapping = SimpleUrlHandlerMapping()
        handlerMapping.urlMap = mapOf(
            "/api/v1/ws/message" to messageSocketHandler
        )
        handlerMapping.order = -1
        return handlerMapping
    }
}