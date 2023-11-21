package com.riti.backendforfrontend.mapper

import com.riti.backendforfrontend.repository.entity.Inbox
import com.riti.backendforfrontend.repository.entity.Message
import com.riti.data.dto.*
import org.mapstruct.*


@Mapper
interface MessageConverter {
    /**
     * entity -> dto
     */
    fun convertToGetMessageDto(message: Message): GetMessageDto.GetMessage
    fun convertToGetInboxDto(inbox: Inbox): GetInboxDto.GetInbox

    /**
     * dto -> entity
     */
    @Mappings(
        Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    )
    fun convertToEntity(dto: SaveMessageDto.SaveMessageRequest): Message

    /**
     * dto -> dto
     */
    @Mappings(
        Mapping(target = "userId", source = "receiverId")
    )
    fun covertToSaveMessageRequest(request: SocketMessageDto.SocketMessageRequestDto): SaveMessageDto.SaveMessageRequest

    @Mappings(
    )
    fun covertToUpdateMessageInfoRequestDto(request: SocketMessageDto.SocketMessageRequestDto): UpdateMessageInfoDto.UpdateMessageInfoRequestDto

    @Mappings(
    )
    fun covertToSocketResponseDto(request: SocketMessageDto.SocketMessageRequestDto): SocketMessageDto.SocketMessageResponseDto

}