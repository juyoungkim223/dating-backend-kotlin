package com.riti.backendforfrontend.repository

import com.riti.backendforfrontend.controller.UserController
import com.riti.backendforfrontend.repository.entity.User
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono

@WebFluxTest(UserRepository::class)
class UserRepositoryTests {
    @MockBean
    lateinit var userRepository: UserRepository

    @Test
    fun select() {
        //val res = userRepository.findById("testid1");
        println("1");

    }
    @Test
    fun save() {

       //userRepository.save(User("testid1", "jy"))
                //.willReturn(Mono.just(User("testid1", "jy")))
    }
}