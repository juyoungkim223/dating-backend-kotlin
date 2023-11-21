package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.riti.data.exception.ApiRuntimeException

enum class EmailTypeEnum(val type: String
, val subject: String
, val content: String) {
    REGISTER("REGISTER", "", ""),
    CHANGE_PASSWORD("CHANGE_PASSWORD", "", ""),
    NONE("NONE", "", "");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(name: String?): EmailTypeEnum =
            values().firstOrNull { it.type == name } ?: NONE
    }
}