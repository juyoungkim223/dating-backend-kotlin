package com.riti.data.dto

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import java.time.LocalDateTime
import java.util.*

class SignUpUserDto{
    data class SignUpUserRequest (
        @field:Email
        @Schema(example = "car22332@gmail.com")
        val userId: String,
        @Schema(example = "kimpark123")
        val password: String,
        // basic
        @Schema(example = "0")
        val company: String,
        @Schema(example = "서경대학교")
        val school: String,
        @Schema(example = "김주영")
        val name: String,
        @Schema(example = "19950223")
        val birthDate: String,
        @Schema(example = "M")
        val gender: String,
        @Schema(example = "경기도 김포시")
        val address: String,
        @Schema(example = "178")
        val height: String,
        // prefer age
        @Schema(example = "20")
        val ageRangeStart: String,
        @Schema(example = "30")
        val ageRangeEnd: String,
        // life style
        @Schema(example = "0")
        val drinking: String,
        @Schema(example = "0")
        val smoking: String,
        @Schema(example = "0")
        val personalityType: String,
        @Schema(example = "0")
        val workout: String,
        @Schema(example = "0")
        val communicationStyle: String,
        // interest
        @Schema(example = "1")
        val interest1: String,
        @Schema(example = "2")
        val interest2: String,
        @Schema(example = "3")
        val interest3: String,
        @Schema(example = "4")
        val interest4: String,
        @Schema(example = "5")
        val interest5: String,
        // photo
        @Schema(example = "photo1")
        val photo1: String,
        @Schema(example = "photo1")
        val photo2: String,
        @Schema(example = "photo1")
        val photo3: String,
        @Schema(example = "photo1")
        val photo4: String,
        @Schema(example = "photo1")
        val photo5: String,
        @Schema(example = "photo1")
        val photo6: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        ) {
    }

    data class SignUpUserResponse(
        val loginToken: String
        ) {

    }
}
