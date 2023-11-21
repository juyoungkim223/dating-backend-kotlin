package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * drinking
 */
enum class DrinkingEnum(val code: Int, val description: String) {
    I_DON_T_DRINK(0, "I don`t drink"),
    I_SOMETIMES_DRINK(1, "I sometimes drink"),
    I_OFTEN_DRINK(2, "I often drink"),
    I_DRINK_EVERY_DAY(3, "I drink every day"),
    I_EVEN_DRINK_ALONE(4, "I even drink alone"),
    ONLY_SOCIALLY(5, "Only socially"),
    CURRENTLY_ABSTINENT(6, "Currently abstinent"),
    NONE(-1, "NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): DrinkingEnum =
            values().firstOrNull { it.code == code } ?: NONE
    }
}