package com.riti.data.dto

import java.time.LocalDateTime

class RecommendUserDto {
    data class RecommendUserResponse(
        var users: List<RecommendUser>
    ){
    }

    data class RecommendUser(
        val userId: String,
        // college student does not have company
        val company: String,
        val school: String,
        // representation of yours
        val name: String,
        //1995-02-23
        val birthDate: String,
        //M or F
        val gender: String,
        val address: String,
        val height: String,
        // Y or N
        val smoking: String,
        // photo
        val photo1: String,
        val photo2: String,
        val photo3: String,
        val photo4: String,
        val photo5: String,
        val photo6: String,
        // tracking info
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    ) {
    }

    data class RecommendUserRequest(
        val userId: String,
        // data would send by client has recommended user list
        val recommendedUserIdList: List<String>
        ){
    }
}
