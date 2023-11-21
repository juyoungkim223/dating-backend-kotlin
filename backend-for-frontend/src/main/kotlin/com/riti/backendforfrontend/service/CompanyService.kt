package com.riti.backendforfrontend.service

import com.riti.data.enums.CompanyEnum
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class CompanyService() {
    suspend fun getCompanies(): Array<CompanyEnum> {
        return CompanyEnum.getCompanies()
    }

}
