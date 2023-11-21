package com.riti.backendforfrontend.service

import com.riti.backendforfrontend.external.FcmApiClient
import com.riti.backendforfrontend.mapper.FcmTokenConverter
import com.riti.backendforfrontend.repository.FcmTokenRepository
import com.riti.backendforfrontend.repository.entity.FcmToken
import com.riti.data.dto.PushNotificationDto
import com.riti.data.dto.SaveFcmTokenDto
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * https://firebase.google.com/docs/cloud-messaging/flutter/client?hl=ko
 * FCM has topic and token based system
 * 기기 고유값 token 받고 천송
 */
@Service
@Slf4j
class NotificationService(val fcmTokenRepository: FcmTokenRepository
, val fcmApiClient: FcmApiClient) {
    private val fcmTokenConverter = Mappers.getMapper(FcmTokenConverter::class.java)

    suspend fun sendNotification(token: String, message: String, createdAt: LocalDateTime): Any? {
        // send to FCM
        fcmApiClient.sendMessageToToken(PushNotificationDto.PushNotificationRequest("Dating", message, null, token))
        // Save fcm sending log
        return null
    }

    suspend fun saveFcmToken(request: SaveFcmTokenDto.SaveFcmTokenRequestDto): FcmToken {
        val res = fcmTokenRepository.save(fcmTokenConverter.convertToEntity(request))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_SAVE_FCM_TOKEN)
        return res
    }

    suspend fun getFcmToken(userId: String): FcmToken {
        val res = fcmTokenRepository.findById(userId)
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_GET_FCM_TOKEN)
        return res
    }

}
