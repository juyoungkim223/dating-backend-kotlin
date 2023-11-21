package com.riti.backendforfrontend.service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class UserServiceTests {
    @MockBean
    lateinit var userService: UserService
    @Test
    suspend fun sendMail() {


    }
}