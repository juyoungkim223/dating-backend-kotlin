package com.riti.backendforfrontend.facade

import com.riti.backendforfrontend.repository.entity.Authentication
import com.riti.backendforfrontend.repository.entity.User
import com.riti.backendforfrontend.service.CompanyService
import com.riti.backendforfrontend.service.EmailService
import com.riti.backendforfrontend.service.TokenService
import com.riti.backendforfrontend.service.UserService
import com.riti.data.dto.*
import com.riti.data.enums.CompanyEnum
import com.riti.data.util.Util
import org.springframework.stereotype.Component

@Component
class UserFacade(private val userService: UserService
, private val emailService: EmailService
, private val companyService: CompanyService
, private val tokenService: TokenService){
    suspend fun sendEmailWithAuthCode(request: SendEmailDto.SendEmailRequest): Boolean {
        // 6자리 문자열 랜덤 생성
        val randomizeKey:String = Util.randomAlphabetNumber(5)
        // 1. 이메일 전송
        val r1 = emailService.sendEmail(randomizeKey, request);
        // 2. 인증 코드값 저장
        val r2 = userService.saveEmailAuthCode(randomizeKey, request);
        return true
    }

    suspend fun authenticateEmailCode(request: com.riti.data.dto.AuthenticateEmailCodeDto.AuthenticateEmailCodeRequest): Authentication {
        return userService.authenticateEmailCode(request)
    }

    suspend fun signUpUser(request: SignUpUserDto.SignUpUserRequest): String {
        val user = userService.saveUser(request)
        return tokenService.createJWT(user.userId);
    }

    suspend fun getCompanies(): Array<CompanyEnum> {
        return companyService.getCompanies();
    }

    suspend fun getUser(userId: String): User {
        return userService.getUser(userId);
    }

    suspend fun updateUser(request: UpdateUserDto.UpdateUserRequest): User {
        return userService.updateUser(request)
    }

    suspend fun deleteUser(userId: String): User {
        // 1. delete user
        var user = userService.deleteUser(userId)
        // 2. delete authentication
        // 3. purchase_history
        return user
    }

    suspend fun signInUser(request: SignInUserDto.SignInUserRequest): String {
        // 1. 유저 조회
        val user = this.getUser(request.userId)
        // 2. 유저 로그인 성공
        userService.signInUser(request)
        // 3. JWT 발급
        return tokenService.createJWT(user.userId);
    }
}
