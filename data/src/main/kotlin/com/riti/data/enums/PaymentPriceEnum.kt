package com.riti.data.enums

/**
 * 인앱 구매 가격
 */
enum class PaymentPriceEnum(val price: Int, val itemType: ConsumableItemTypeEnum) {
    RECOMMEND_GOLD(2, ConsumableItemTypeEnum.GOLD),
    NONE(0, ConsumableItemTypeEnum.NONE);
}