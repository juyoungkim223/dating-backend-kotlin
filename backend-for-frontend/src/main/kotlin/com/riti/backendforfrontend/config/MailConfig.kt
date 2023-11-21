package com.riti.backendforfrontend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*


@Configuration
class MailConfig {
    @Value("\${spring.mail.host}")
    lateinit var host: String
    @Value("\${spring.mail.port}")
    lateinit var port: String
    @Value("\${spring.mail.username}")
    lateinit var username: String
    @Value("\${spring.mail.password}")
    lateinit var password: String
    @Value("\${spring.mail.protocol}")
    lateinit var protocol: String
    @Value("\${spring.mail.default-encoding}")
    lateinit var defaultEncoding: String

    @Bean
    fun javaMailService(): JavaMailSender {
        val javaMailSender = JavaMailSenderImpl()
        javaMailSender.host = host
        javaMailSender.username = username
        javaMailSender.password = password
        javaMailSender.port = port.toInt()
        javaMailSender.protocol = protocol
        javaMailSender.defaultEncoding = defaultEncoding
        javaMailSender.javaMailProperties = mailProperties
        return javaMailSender
    }

    private val mailProperties: Properties
        private get() {
            val properties = Properties()
            properties.setProperty("mail.transport.protocol", "smtp")
            properties.setProperty("mail.smtp.auth", "true")
            properties.setProperty("mail.smtp.starttls.enable", "true")
            properties.setProperty("mail.debug", "true")
            properties.setProperty("mail.smtp.ssl.trust", host)
            properties.setProperty("mail.smtp.ssl.enable", "true")
            return properties
        }
}