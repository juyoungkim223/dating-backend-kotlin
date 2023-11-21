package com.riti.backendforfrontend.controller

import com.riti.data.dto.ApiResponse
import com.riti.data.dto.PurchaseDto
import com.riti.data.dto.RecommendUserDto
import io.swagger.v3.oas.annotations.Operation
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 인앱 통화 결제
 */
@RestController
@RequestMapping("/api/v1/purchase")
@Slf4j
class PurchaseController {
    /**
     * purchase consumable
     */
    @Operation(summary = "Purchase consumable", description = "Purchase consumable")
    @GetMapping("/purchase/consumable")
    suspend fun purchase(request: PurchaseDto.PurchaseRequestDto): ApiResponse<PurchaseDto.PurchaseResponseDto> {

        return ApiResponse.ok(PurchaseDto.PurchaseResponseDto(""));
    }
}