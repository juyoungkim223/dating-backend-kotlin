package com.riti.backendforfrontend.controller

import com.riti.backendforfrontend.facade.MatchingFacade
import com.riti.backendforfrontend.mapper.MatchingConverter
import com.riti.data.dto.*
import io.swagger.v3.oas.annotations.Operation
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 매칭, 추천, 좋아요, 싫어요
 */
@RestController
@RequestMapping("/api/v1/match")
@Slf4j
class MatchController (private val matchingFacade: MatchingFacade) {
    private val logger = LoggerFactory.getLogger(MatchController::class.java)
    private val matchingConverter = Mappers.getMapper(MatchingConverter::class.java)

    /**
     * 특정 시간마다 자동 추천 - 신규 추천 (기본 1명, 12시간마다 추가 추천)
     * 조건
     * 1. 기존 추천 하지 않은 유저
     * 2. 선호연령 우선 조회, 없다면 연령무관 조회
     * 기존 추천 유저 제외, 추천 기록 (과거 추천 목록 보여주는 용도, 이미 추천한 유저 다시 안하는 용도) 은 클라이언트로 부터 요청 받는다.
     * where not in user_id (DB 이슈사항 참고: http://www.gurubee.net/article/85562)
     */
    @Operation(summary = "Recommend user", description = "Recommend user")
    @GetMapping("/recommend")
    suspend fun recommendUser(request: RecommendUserDto.RecommendUserRequest): ApiResponse<RecommendUserDto.RecommendUserResponse> {
        val users = matchingFacade.recommendUser(request);
        val list = ArrayList<RecommendUserDto.RecommendUser>()
        users.forEach{ list.add(matchingConverter.convertToRecommendUserDto(it)) }
        return ApiResponse.ok(RecommendUserDto.RecommendUserResponse(list));
    }

    /**
     * 추가 추천 - 통화 지불
     * 조건
     * 1. 이미 추천된 리스트에 없는 유저
     * 2. 선호연령 우선 조회, 없다면 연령무관 조회
     */

    @Operation(summary = "Recommend user with payment", description = "Recommend user with payment")
    @GetMapping("/recommendWithPayment")
    suspend fun recommendUserWithPayment(request: RecommendUserWithPaymentDto.RecommendUserWithPaymentRequest): ApiResponse<RecommendUserDto.RecommendUserResponse> {
        val users = matchingFacade.recommendUserWithPayment(request);
        val list = ArrayList<RecommendUserDto.RecommendUser>()
        users.forEach{ list.add(matchingConverter.convertToRecommendUserDto(it)) }
        return ApiResponse.ok(RecommendUserDto.RecommendUserResponse(list));
    }

    /**
     * Like
     * 1. Like 데이터 저장
     * 2. FCM 전송
     * - UnLike 어떠한 행동도 필요 없다
     * - UnLike: 단순히 좋아요의 반대 개념이며 유저A가 거절한 유저B가 유저A를 좋아하는 경우 유저A에게 좋아요를 누른 유저 리스트에는 조회된다.
     * 좋아하는 유저 리스트에 조차 나오지 않으려면 unlike가 아닌 block 개념이 필요하다
     */
    @Operation(summary = "Like another user", description = "Like another user")
    @PostMapping("/like")
    @ResponseStatus(HttpStatus.OK)
    suspend fun likeTheUser(@RequestBody request: LikeTheUserDto.LikeTheUserRequest): ApiResponse<Boolean> {
        return ApiResponse.ok(true)
    }

}