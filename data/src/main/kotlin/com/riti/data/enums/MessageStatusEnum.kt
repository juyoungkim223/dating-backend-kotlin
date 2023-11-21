package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class MessageStatusEnum(val code: String) {
    READ("READ"),
    UNREAD("UNREAD"),
    NONE("NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: String?): MessageStatusEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}