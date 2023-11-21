package com.riti.data.dto

class PurchaseDto {
    data class PurchaseRequestDto(
        val userId: String) {
        }
    data class PurchaseResponseDto(
        val userId: String) {
    }
}
