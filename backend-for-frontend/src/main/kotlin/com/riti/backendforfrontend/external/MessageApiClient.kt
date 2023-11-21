package com.riti.backendforfrontend.external

import com.riti.data.dto.ApiResponse
import com.riti.data.dto.SaveMessageDto
import com.riti.data.dto.SocketMessageDto
import com.riti.data.dto.UpdateMessageInfoDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Mono

@Component
class MessageApiClient {
    @Value("\${message.api.url}")
    private val messageApiUrl: String = ""
    private val webClient = WebClient.builder()
        .baseUrl(messageApiUrl)
        .build()

    suspend fun sendMessageToConnectedUser(request: SocketMessageDto.SocketMessageResponseDto, connectedServer: String): ApiResponse<Boolean> =
        webClient.post()

            .uri("/api/v1/message/sendMessageToConnectedUser")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<ApiResponse<Boolean>>()

}