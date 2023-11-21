package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * smoking
 */
enum class SmokingEnum(val code: Int, val description: String) {
    SOCIAL_SMOKER(0, "Social smoker"),
    SMOKER_WHEN_DRINKING(1, "Smoker when drinking"),
    NON_SMOKER(2, "Non-smoker"),
    SMOKER(3, "Smoker"),
    TRYING_TO_QUIT(4, "Trying to quit"),
    NONE(-1, "NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): SmokingEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}