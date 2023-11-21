package com.riti.data.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

class SaveMessageDto {
    data class SaveMessageRequest (
        @Schema(example = "1")
        val inboxId: Long,
        @Schema(example = "car22332@gmail.com")
        val userId: String,
        @Schema(example = "testMessage")
        val content: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        ){
    }

}
