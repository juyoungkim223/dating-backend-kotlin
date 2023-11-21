package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.riti.data.exception.ApiRuntimeException

enum class GenderEnum(val code: String) {
    MALE("M"),
    FEMALE("F"),
    NONE("NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: String?): GenderEnum =
            values().firstOrNull { it.code == code } ?: NONE

        @JvmStatic
        fun oppositeGender(gender: String): GenderEnum {
            return if (gender == MALE.code)
                FEMALE
            else if (gender == FEMALE.code)
                MALE
            else
                NONE
        }
    }
}