package com.riti.backendforfrontend.config


import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.*

import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException


/**
 * FCM 채팅 푸시 용도
 */
@Configuration
class FcmConfig {
    @Value("\${app.firebase-configuration-file}")
    private val firebaseConfigPath: String? = null

    var logger: Logger = LoggerFactory.getLogger(com.riti.backendforfrontend.config.FcmConfig::class.java)
    // firebase admin sdk init
    @PostConstruct
    fun initialize() {
        try {
            val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(ClassPathResource(firebaseConfigPath!!).inputStream)).build()
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                logger.info("Firebase application has been initialized")
            }
        } catch (e: IOException) {
            logger.error(e.message)
        }
    }

}