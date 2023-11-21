package com.riti.data.dto

import io.swagger.v3.oas.annotations.media.Schema

class SaveFcmTokenDto {
    data class SaveFcmTokenRequestDto(
        @Schema(example = "car22332@gmail.com")
        val userId: String,
        @Schema(example = "testtoken")
        val fcmToken: String
    ){

    }
}
