package com.riti.backendforfrontend.mapper

import com.riti.backendforfrontend.repository.entity.FcmToken
import com.riti.backendforfrontend.repository.entity.Message
import com.riti.data.dto.*
import org.mapstruct.*


@Mapper
interface FcmTokenConverter {
    /**
     * entity -> dto
     */
    fun convertToGetMessageDto(message: Message): GetMessageDto.GetMessage
    fun convertToGetMessageRoomDto(message: Message): GetInboxDto.GetInbox

    /**
     * dto -> entity
     */
    @Mappings(
        Mapping(target = "new", constant = "true"),
        Mapping(target = "createdAt", expression = "java(LocalDateTime.now())"),
        Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    )
    fun convertToEntity(dto: SaveFcmTokenDto.SaveFcmTokenRequestDto): FcmToken


}