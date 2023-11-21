package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class MessageTypeEnum(val code: String) {
    SEND("SEND"),
    READ("READ"),
    NONE("NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: String?): MessageTypeEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}