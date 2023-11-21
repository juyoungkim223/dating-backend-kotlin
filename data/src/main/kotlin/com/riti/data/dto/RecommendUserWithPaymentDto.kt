package com.riti.data.dto

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

class RecommendUserWithPaymentDto {
    data class RecommendUserWithPaymentResponse(
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
        val drinkingCapacity: String,
        // Y or N
        val smoking: String,
        val mbti: String,
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

    data class RecommendUserWithPaymentRequest(
        val userId: String,
        val recommendedUserIdList: List<String>,
        @Schema(description = "gold amount user has, only for validation to check whether user has been cheated or not", example = "2")
        val gold: Int
        ){
    }
}
