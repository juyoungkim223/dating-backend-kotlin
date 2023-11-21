package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * PersonalityTypeEnum
 */
enum class PersonalityTypeEnum(val code: Int) {
    INTJ(0),
    INTP(1),
    ENTJ(2),
    ENTP(3),
    INFJ(4),
    INFP(5),
    ENFJ(6),
    ENFP(7),
    ISTJ(8),
    ISFJ(9),
    ESTJ(10),
    ESFJ(11),
    ISTP(12),
    ISFP(13),
    ESTP(14),
    ESFP(15),
    NONE(-1);
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): PersonalityTypeEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}