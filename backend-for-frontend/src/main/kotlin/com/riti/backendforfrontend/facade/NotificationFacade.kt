package com.riti.backendforfrontend.facade

import com.riti.backendforfrontend.repository.entity.FcmToken
import com.riti.backendforfrontend.service.NotificationService
import com.riti.data.dto.*
import org.springframework.stereotype.Component


@Component
class NotificationFacade(private val notificationService: NotificationService) {
    /**
     * save fcm token
     */
    suspend fun saveFcmToken(request: SaveFcmTokenDto.SaveFcmTokenRequestDto): FcmToken {
        return notificationService.saveFcmToken(request)
    }
    /**
     * send fcm
     */
    suspend fun sendFcm(request: SaveMessageDto.SaveMessageRequest): Boolean {
        // Get fcm token from DB
        val fcmToken = notificationService.getFcmToken(request.userId)
        // Send fcm
        //var notificationRes = notificationService.sendNotification(fcmToken.fcmToken, request.content, LocalDateTime.now())
        return true
    }
}
