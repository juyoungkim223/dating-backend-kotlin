package com.riti.data.dto

class GetCompanyDto {
    data class GetCompanyRequest (val domain:String) {

    }
    data class GetCompanyResponse(val companyDto: List<CompanyDto?>) {

    }
    data class CompanyDto( val code: Int
        , val emailDomain: String?
        , val companyName: String?)

}
