package com.riti.data.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

class GetInboxDto {
    data class GetInboxRequestDto(
        @Schema(example = "car22332@gmail.com")
        val userId: String,
        @Schema(description = "offset based pagination", example = "20")
        val cursor: Long?
    ){}


    data class GetInboxResponseDto(
        var inboxes: List<GetInbox>
    ){}

    data class GetInbox(
        val lastMessage: String,
        val lastSentUserId: String
    ) {

    }
}
