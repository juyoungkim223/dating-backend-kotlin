package com.riti.backendforfrontend.mapper

import com.riti.backendforfrontend.repository.entity.User
import com.riti.data.dto.DeleteUserDto
import com.riti.data.dto.GetUserDto
import com.riti.data.dto.SignUpUserDto
import com.riti.data.dto.UpdateUserDto
import org.mapstruct.*


@Mapper
interface UserConverter {
    /**
     * entity -> dto
     */
    fun convertToUpdateDto(user: User): UpdateUserDto.UpdateUserResponse
    fun convertToSignUpDto(user: User): SignUpUserDto.SignUpUserResponse
    fun convertToGetDto(user: User): GetUserDto.GetUserResponse
    fun convertToDeleteDto(user: User): DeleteUserDto.DeleteUserResponse

    /**
     * dto -> entity
     */
    @InheritInverseConfiguration
    fun convertToEntity(dto: UpdateUserDto.UpdateUserRequest): User
    @Mappings(
        Mapping(target = "password", expression = "java(com.riti.data.util.Util.encryptUserPassword(dto.getPassword(), hmacKey))"),
        Mapping(target = "new", constant = "true")
    )
    fun convertToEntity(dto: SignUpUserDto.SignUpUserRequest, hmacKey: String): User


}