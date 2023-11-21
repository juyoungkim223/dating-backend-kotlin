package com.riti.data.dto

import com.riti.data.enums.MessageStatusEnum

class UpdateMessageInfoDto {
    data class UpdateMessageInfoRequestDto(
        val messageId: Long,
        val receiverId: String,
        val messageStatus: MessageStatusEnum
    ) {
    }
}
