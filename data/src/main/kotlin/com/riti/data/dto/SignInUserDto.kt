package com.riti.data.dto

class SignInUserDto {
    data class SignInUserRequest(
        val userId: String,
        val password: String
    ) {

    }
    data class SignInUserResponse(
        val token: String
    ) {

    }
}
