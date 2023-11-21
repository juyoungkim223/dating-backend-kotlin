package com.riti.backendforfrontend.controller

import com.riti.backendforfrontend.facade.NotificationFacade
import com.riti.backendforfrontend.mapper.MessageConverter
import com.riti.data.dto.*
import io.swagger.v3.oas.annotations.Operation
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 푸시
 * 1. FCM
 * */
@RestController
@RequestMapping("/api/v1/notification")
@Slf4j
class NotificationController(private val notificationFacade: NotificationFacade) {
    private val logger = LoggerFactory.getLogger(MatchController::class.java)

    /**
     * Save FCM Token, this method will be called by client app
     */
    @Operation(summary = "Save FCM Token", description = "Save FCM Token")
    @PostMapping("/saveFcmToken")
    @ResponseStatus(HttpStatus.OK)
    suspend fun saveFcmToken(@RequestBody request: SaveFcmTokenDto.SaveFcmTokenRequestDto): ApiResponse<Boolean> {
        notificationFacade.saveFcmToken(request)
        return ApiResponse.ok(true)
    }
}