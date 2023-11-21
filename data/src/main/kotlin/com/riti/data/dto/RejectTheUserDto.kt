package com.riti.data.dto

class RejectTheUserDto {
    data class RejectTheUserRequest (
        val idOfUserWhoSentReject: String,
        val idOfUserWhoGotReject: String,
        ){

    }
}
