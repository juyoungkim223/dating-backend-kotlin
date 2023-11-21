package com.riti.backendforfrontend.facade

import com.riti.backendforfrontend.repository.entity.User
import com.riti.backendforfrontend.service.PaymentService
import com.riti.backendforfrontend.service.UserService
import com.riti.data.dto.RecommendUserDto
import com.riti.data.dto.RecommendUserWithPaymentDto
import com.riti.data.enums.GenderEnum
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Component

@Component
class MatchingFacade(private val userService: UserService
, private val paymentService: PaymentService) {
    suspend fun recommendUser(request: RecommendUserDto.RecommendUserRequest): List<User> {
        // 추천 요청 유저 조회
        val user = userService.getUser(request.userId)
        // 추천 유저 조회
        val res = userService.recommendUser(GenderEnum.oppositeGender(user.gender).code, user.ageRangeStart, user.ageRangeEnd, request.recommendedUserIdList)
        return res.toList()
    }

    suspend fun recommendUserWithPayment(request: RecommendUserWithPaymentDto.RecommendUserWithPaymentRequest): List<User> {
        // 자산 차감
        val inventory = paymentService.useGold(request)
        // 추천 요청 유저 조회
        val user = userService.getUser(request.userId)
        // 추천 유저 조회
        val res = userService.recommendUser(GenderEnum.oppositeGender(user.gender).code, user.ageRangeStart, user.ageRangeEnd, request.recommendedUserIdList)
        return res.toList()
    }
}
