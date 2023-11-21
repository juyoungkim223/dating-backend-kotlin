package com.riti.data.dto

class PushNotificationDto {
    data class PushNotificationRequest(
        val title: String,
        val message: String,
        val topic: String?,
        val token: String?
    ) {

    }
}