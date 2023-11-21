package com.riti.data.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

class GetMessageDto {
    data class GetMessageRequestDto(
        val inboxId: Long,
        @Schema(description = "cursor based pagination", example = "10")
        val cursor: Long?
    ){}


    data class GetMessageResponseDto(
        var messages: List<GetMessageDto.GetMessage>,
        var pageNo: String?
    ){}

    data class GetMessage (
        val content: String,
        val createdAt: LocalDateTime
    ){

    }
}
