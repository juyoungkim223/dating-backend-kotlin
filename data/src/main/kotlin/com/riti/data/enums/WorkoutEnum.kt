package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * WorkoutEnum
 */
enum class WorkoutEnum(val code: Int, val description: String) {
    EVERYDAY(0, "Everyday"),
    OFTEN(1, "Often"),
    SOMETIMES(2, "Sometimes"),
    NEVER(3, "Never"),
    NONE(-1, "NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): WorkoutEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}