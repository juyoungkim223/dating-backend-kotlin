package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * SchoolEnum
 */
enum class SchoolEnum(val code: Int, val description: String) {
    SEOUL_UNIV(0, "SeoulUniversity"),
    NONE(-1, "NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): SchoolEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}