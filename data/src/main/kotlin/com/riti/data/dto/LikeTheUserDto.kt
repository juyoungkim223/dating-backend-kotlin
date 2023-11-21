package com.riti.data.dto

class LikeTheUserDto {
    data class LikeTheUserRequest (
        val idOfUserWhoSentLike: String,
        val idOfUserWhoGotLike: String,
        ){

    }
}
