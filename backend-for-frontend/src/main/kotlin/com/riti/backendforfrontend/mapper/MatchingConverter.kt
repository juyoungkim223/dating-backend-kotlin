package com.riti.backendforfrontend.mapper

import com.riti.backendforfrontend.repository.entity.User
import com.riti.data.dto.RecommendUserDto
import org.mapstruct.*


@Mapper
interface MatchingConverter {
    /**
     * entity -> dto
     */
    fun convertToRecommendUserDto(user: User): RecommendUserDto.RecommendUser



    /**
     * dto -> entity
     */



}