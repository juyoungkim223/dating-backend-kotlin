package com.riti.backendforfrontend.controller;

import com.riti.backendforfrontend.repository.entity.Authentication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest
public class UserControllerTests {
    lateinit var client: WebTestClient

    @BeforeEach
    fun setUp(context: ApplicationContext) {
        client = WebTestClient.bindToApplicationContext(context).build()
    }

    @Test
    fun emailAuth() {
        val userId = "car@a.com";

        client.get().uri("/api/v1/user/emailAuth?email=car@a.com&authCode=LrHVoq")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
    }
}
