package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * CompanyEnum
 */
enum class CompanyEnum(val code: Int
, val emailDomain: String
, val description: String) {
    NAVER(0, "navercorp.com","Naver"),
    TOSS(1, "toss.com","Toss"),
    GMARKET(2, "gmarket.com","Gmarket"),
    COUPANG(3, "coupang.com","Coupang"),
    NONE(-1, "NONE","NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): CompanyEnum =
            values().firstOrNull { it.code == code } ?: NONE

        @JvmStatic
        fun getCompanies() = values()
    }
}