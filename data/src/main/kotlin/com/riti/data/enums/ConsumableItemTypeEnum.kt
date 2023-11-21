package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class ConsumableItemTypeEnum(val code: String) {
    GOLD("GOLD"),
    NONE("NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: String?): ConsumableItemTypeEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}