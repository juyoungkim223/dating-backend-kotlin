package com.riti.data.dto
import com.fasterxml.jackson.annotation.JsonCreator
import com.riti.data.enums.EmailTypeEnum
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank



class AuthenticateEmailCodeDto {
        data class AuthenticateEmailCodeRequest @JsonCreator constructor(
            // email address
            @field:Email
            val email: String,
            @field:NotBlank(message = "auth code should be not blank")
            val authCode: String,
            @field:NotBlank(message = "email type should be not blank")
            val emailType: EmailTypeEnum
    )
}