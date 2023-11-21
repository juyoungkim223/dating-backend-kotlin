package com.riti.backendforfrontend.controller

import com.riti.backendforfrontend.facade.UserFacade
import com.riti.backendforfrontend.mapper.UserConverter
import com.riti.data.dto.*
import com.riti.data.enums.EmailTypeEnum
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.flow.*
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
class UserController(private val userFacade: UserFacade) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)
    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    /**
     * 이메일 인증 전송 요청.
     * 사용 용도
     * 1. 가입 전 이메일 인증. 어느 회사, 어느 학교인지 인증 용도
     * 2. 비밀번호 찾기로 변경
     * email address 사용자 식별.
     */
    @Operation(summary = "Send Email with AuthCode", description = "Send Email with AuthCode")
    @PostMapping("/emailAuth")
    @ResponseStatus(HttpStatus.OK)
    suspend fun sendEmailForAuth(@RequestBody request: SendEmailDto.SendEmailRequest): ApiResponse<Boolean> {
        if(request.emailType == EmailTypeEnum.NONE) throw ApiRuntimeException(ResultCode.SEND_MAIL_FAILED_BY_EMAIL_TYPE)
        return ApiResponse.ok(userFacade.sendEmailWithAuthCode(request))
    }

    /**
     * 가입 전 이메일 인증 값 검증 요청
     */
    @Operation(summary = "Validate Email AuthCode for signup", description = "Validate Email AuthCode for signup")
    @GetMapping("/emailAuth")
    suspend fun authenticateEmailCode(request: com.riti.data.dto.AuthenticateEmailCodeDto.AuthenticateEmailCodeRequest): ApiResponse<Boolean> {
        userFacade.authenticateEmailCode(request)
        return ApiResponse.ok(true)
    }

    /**
     * 가입 가능한 메일 리스트 조회
     */
    @Operation(summary = "Get company list", description = "Get company list")
    @GetMapping("/companies")
    suspend fun getMailList(): ApiResponse<GetCompanyDto.GetCompanyResponse> {
        return ApiResponse
            .ok(GetCompanyDto.GetCompanyResponse(userFacade.getCompanies()
                .map { GetCompanyDto.CompanyDto(it.code, it.emailDomain, it.description) }
                .toList()))
    }

    /**
     * 회원 가입
     */
    @Operation(summary = "SignUp User", description = "SignUp User")
    @PostMapping("signUp")
    @ResponseStatus(HttpStatus.OK)
    suspend fun signUp(@RequestBody request: SignUpUserDto.SignUpUserRequest): ApiResponse<SignUpUserDto.SignUpUserResponse> {
        return ApiResponse.ok(SignUpUserDto.SignUpUserResponse(userFacade.signUpUser(request)))
    }

    /**
     * 회원 로그인 (JWT 발급)
     */
    @Operation(summary = "SignIn user", description = "SignIn user")
    @PostMapping("/signIn")
    suspend fun signInUser(@RequestBody request: SignInUserDto.SignInUserRequest): ApiResponse<SignInUserDto.SignInUserResponse> {
        logger.debug("request => {}", request)
        val res = userFacade.signInUser(request)
        return ApiResponse.ok(SignInUserDto.SignInUserResponse(res))
    }

    /**
     * 회원 수정
     */
    @Operation(summary = "Update user", description = "Update user")
    @PutMapping("")
    suspend fun updateUser(@RequestBody request: UpdateUserDto.UpdateUserRequest): ApiResponse<UpdateUserDto.UpdateUserResponse> {
        val res = userFacade.updateUser(request)
        return ApiResponse.ok(userConverter.convertToUpdateDto(res))
    }

    /**
     * 회원 조회 - 회원정보 수정 페이지
     */
    @Operation(summary = "Get user", description = "Get user")
    @GetMapping("")
    suspend fun getUser(@RequestParam("userId") userId: String): ApiResponse<GetUserDto.GetUserResponse> {
        val res = userFacade.getUser(userId)
        return ApiResponse.ok(userConverter.convertToGetDto(res))
    }
    /**
     * 회원 탈퇴
     */
    @Operation(summary = "Delete user", description = "Delete user")
    @DeleteMapping("")
    suspend fun deleteUser(@RequestParam("userId") userId: String): ApiResponse<DeleteUserDto.DeleteUserResponse> {
        val res = userFacade.deleteUser(userId)
        return ApiResponse.ok(userConverter.convertToDeleteDto(res))
    }

}
