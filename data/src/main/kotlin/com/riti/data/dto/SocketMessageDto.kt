package com.riti.data.dto

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.riti.data.enums.MessageStatusEnum
import com.riti.data.enums.MessageTypeEnum

class SocketMessageDto {
    data class SocketMessageRequestDto(
        // success of fail response for message
        val clientMessageId: String,
        val inboxId: Long,
        var messageId: Long,
        val senderId: String,
        val receiverId: String,
        val content: String,
        val messageStatus: MessageStatusEnum,
        val messageType: MessageTypeEnum
    ){
        companion object {
            fun toJson(jsonText: String): SocketMessageRequestDto {
                return jacksonObjectMapper().readValue<SocketMessageRequestDto>(jsonText)
            }
        }
    }


    data class SocketMessageResponseDto(
        // success of fail response for message
        val clientMessageId: String,
        val inboxId: Long,
        var messageId: Long,
        val senderId: String,
        val receiverId: String,
        val content: String,
        val messageStatus: MessageStatusEnum,
        val messageType: MessageTypeEnum
    ){}


}
