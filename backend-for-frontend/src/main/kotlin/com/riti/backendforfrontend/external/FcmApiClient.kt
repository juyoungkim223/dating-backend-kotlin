package com.riti.backendforfrontend.external

import com.google.firebase.messaging.*
import com.google.gson.GsonBuilder
import com.riti.data.dto.PushNotificationDto
import com.riti.data.dto.PushNotificationDto.PushNotificationRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.ExecutionException


@Component
class FcmApiClient {

    private val logger: Logger = LoggerFactory.getLogger(FcmApiClient::class.java)

    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendMessage(data: Map<String, String>, request: PushNotificationRequest) {
        val message = getPreconfiguredMessageWithData(data, request)
        val response: String = sendAndGetResponse(message)
        logger.info("Sent message with data. Topic: " + request.topic + ", " + response)
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendMessageWithoutData(request: PushNotificationRequest) {
        val message = getPreconfiguredMessageWithoutData(request)
        val response: String = sendAndGetResponse(message)
        logger.info("Sent message without data. Topic: " + request.topic + ", " + response)
    }

    /**
     * send using token
     */
    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendMessageToToken(request: PushNotificationRequest) {
        val message: Message = getPreconfiguredMessageToToken(request)
        val response: String = sendAndGetResponse(message)
        logger.info("Sent message to token. Device token: " + request.token + ", " + response)
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    private fun sendAndGetResponse(message: Message): String {
        return FirebaseMessaging.getInstance().sendAsync(message).get()
    }

    private fun getAndroidConfig(topic: String?): AndroidConfig? {
        return AndroidConfig.builder()
            .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
            .setPriority(AndroidConfig.Priority.HIGH)
            .setNotification(AndroidNotification.builder().setSound("default")
                .setColor("#FFFF00").setTag(topic).build()).build()
    }

    fun getApnsConfig(topic: String?): ApnsConfig? {
        return ApnsConfig.builder()
            .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build()
    }

    fun getPreconfiguredMessageToToken(request: PushNotificationRequest): Message {
        return getPreconfiguredMessageBuilder(request).setToken(request.token)
            .build()
    }

    fun getPreconfiguredMessageWithoutData(request: PushNotificationRequest): Message {
        return getPreconfiguredMessageBuilder(request).setTopic(request.topic)
            .build()
    }

    private fun getPreconfiguredMessageWithData(data: Map<String, String>, request: PushNotificationRequest): Message {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.topic)
            .build()
    }

    fun getPreconfiguredMessageBuilder(request: PushNotificationRequest): Message.Builder {
        val androidConfig: AndroidConfig? = getAndroidConfig(request.topic)
        val apnsConfig: ApnsConfig? = getApnsConfig(request.topic)
        return Message.builder()
            .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                (Notification.builder()
                    .setTitle(request.title)
                    .setBody(request.message)
                    .build()))
    }
    /*
    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendMessageToToken(request: PushNotificationRequest) {
            val message: Message = getPreconfiguredMessageToToken(request)
            val gson = GsonBuilder().setPrettyPrinting().create()
            val jsonOutput = gson.toJson(message)
            val response: String? = sendAndGetResponse(message)
    }
    @Throws(InterruptedException::class, ExecutionException::class)
    private fun sendAndGetResponse(message: Message): String? {
            return FirebaseMessaging.getInstance().sendAsync(message).get()
    }
    private fun getPreconfiguredMessageToToken(request: PushNotificationRequest): Message {
            return getPreconfiguredMessageBuilder(request)!!.setToken(request.token)
                .build()
    }
    @Bean
    fun getPreconfiguredMessageBuilder(request: PushNotificationDto.PushNotificationRequest): Message.Builder? {
            val androidConfig: AndroidConfig = getAndroidConfig(request.topic)
            val apnsConfig: ApnsConfig = getApnsConfig(request.topic)
            return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(Notification.builder()
                    .setTitle(request.title)
                    .setBody(request.message)
                    .build())
    }

    private fun getAndroidConfig(topic: String): AndroidConfig {
            return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                    .setSound("default")
                    .setColor("#FFFF00")
                    .setTag(topic).build())
                .build()
    }

    private fun getApnsConfig(topic: String): ApnsConfig {
            return ApnsConfig.builder()
                .setAps(Aps.builder()
                    .setCategory(topic)
                    .setThreadId(topic)
                    .build())
                .build()
    }

*/
}