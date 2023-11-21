package com.riti.backendforfrontend.mapper

import com.riti.backendforfrontend.repository.entity.Inventory
import com.riti.backendforfrontend.repository.entity.Message
import com.riti.data.dto.*
import org.mapstruct.*


@Mapper
interface InventoryConverter {
    /**
     * entity -> dto
     */
    fun convertToGetMessageDto(message: Message): UpdateUserDto.UpdateUserResponse
    fun convertToGetMessageRoomDto(message: Message): UpdateUserDto.UpdateUserResponse

    /**
     * dto -> entity
     */
    @Mappings(
        Mapping(target = "new", constant = "true")
    )
    fun convertToEntity(userId: String, gold: Int): Inventory


}