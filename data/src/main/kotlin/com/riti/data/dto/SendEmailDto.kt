package com.riti.data.dto
import com.fasterxml.jackson.annotation.JsonCreator
import com.riti.data.enums.EmailTypeEnum
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


class SendEmailDto {
        data class SendEmailRequest @JsonCreator constructor(
            // email address
            @field:Email
            @NotBlank(message = "email should be not blank")
            val email: String,
            @NotBlank(message = "emailType should be not blank")
            val emailType: EmailTypeEnum
        ){

        }
}