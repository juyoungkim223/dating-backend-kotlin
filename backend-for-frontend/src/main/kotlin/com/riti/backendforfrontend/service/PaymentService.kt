package com.riti.backendforfrontend.service

import com.riti.backendforfrontend.mapper.InventoryConverter
import com.riti.backendforfrontend.repository.InventoryRepository
import com.riti.backendforfrontend.repository.entity.Inventory
import com.riti.data.dto.RecommendUserWithPaymentDto
import com.riti.data.enums.PaymentPriceEnum
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
@Slf4j
class PaymentService(val inventoryRepository: InventoryRepository) {
    private val inventoryConverter = Mappers.getMapper(InventoryConverter::class.java)
    suspend fun useGold(request: RecommendUserWithPaymentDto.RecommendUserWithPaymentRequest): Inventory {
        var inventory = inventoryRepository.findById(request.userId)
        if(inventory == null) {
            inventory = inventoryRepository.save(inventoryConverter.convertToEntity(userId = request.userId, gold = 2))
        } else {
            if(request.gold != inventory.gold) throw ApiRuntimeException(ResultCode.FAIL_TO_AUTHENTICATE_INVENTORY_GOLD)
            if(inventory.gold >= PaymentPriceEnum.RECOMMEND_GOLD.price) {
                inventory.gold -= PaymentPriceEnum.RECOMMEND_GOLD.price
                inventory = inventoryRepository.save(inventory)
            } else {
                throw ApiRuntimeException(ResultCode.FAILED_BECAUSE_OF_NOT_ENOUGH_GOLD)
            }
        }
        inventory ?: throw ApiRuntimeException(ResultCode.FAIL_TO_SAVE_INVENTORY)
        return inventory
    }

}
