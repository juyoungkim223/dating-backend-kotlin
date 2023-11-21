package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * CommunicationStyleEnum
 */
enum class CommunicationStyleEnum(val code: Int, val description: String) {
    TEXTER(0, "Texter"),
    PHONE_CALLER(1, "Phone caller"),
    VIDEO_CHATTER(2, "Video chatter"),
    IN_PERSON(3, "In person"),
    NONE(-1, "NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): CommunicationStyleEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}