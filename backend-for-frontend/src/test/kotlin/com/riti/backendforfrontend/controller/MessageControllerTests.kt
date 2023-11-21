package com.riti.backendforfrontend.controller;

import com.riti.backendforfrontend.facade.MessageFacade
import com.riti.data.dto.SaveMessageDto
import kotlinx.coroutines.reactor.mono
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class MessageControllerTests {
    lateinit var client: WebTestClient
    @MockBean
    lateinit var messageController: MessageController
    @MockBean
    lateinit var messageFacade: MessageFacade
    @BeforeEach
    fun setUp(context: ApplicationContext) {
        client = WebTestClient.bindToApplicationContext(context).build()
    }

    @Test
    fun saveMessage() {
        val userId = "car@a.com";
        val req = SaveMessageDto.SaveMessageRequest(mono{messageFacade.getId()}.block()!!, "car22332@gmail.com", "car1@gmail.com"
        , "testMessage", "UNREAD")
        client.post().uri("/api/v1/message/sendMessage")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(req, SaveMessageDto.SaveMessageRequest::class.java)
            .exchange()
            .expectStatus().isOk()


    }
}
