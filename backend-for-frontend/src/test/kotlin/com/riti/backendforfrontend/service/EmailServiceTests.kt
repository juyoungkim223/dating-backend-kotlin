package com.riti.backendforfrontend.service

import com.riti.data.util.Util
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EmailServiceTests @Autowired constructor(
        val emailService: EmailService
) {
    @Test
    fun sendMail() {
        val randomizeKey:String = Util.randomAlphabetNumber(6)

        //emailService.sendEmail(randomizeKey, "car22332@gmail.com")
    }
}