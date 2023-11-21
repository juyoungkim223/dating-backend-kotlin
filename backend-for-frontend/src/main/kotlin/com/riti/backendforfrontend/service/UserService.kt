package com.riti.backendforfrontend.service

import com.riti.data.exception.ApiRuntimeException
import com.riti.backendforfrontend.mapper.UserConverter
import com.riti.backendforfrontend.repository.AuthenticationRepository
import com.riti.backendforfrontend.repository.UserRepository
import com.riti.backendforfrontend.repository.entity.Authentication
import com.riti.backendforfrontend.repository.entity.User
import com.riti.data.util.Util
import com.riti.data.dto.*
import com.riti.data.enums.ResultCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
// https://spring.io/blog/2019/04/12/going-reactive-with-spring-coroutines-and-kotlin-flow
// WebFlux with coroutines API in Kotlin
@Service
@Slf4j
class UserService(val userRepository: UserRepository
, val authenticationRepository: AuthenticationRepository
, @Value("\${hmac.key}") private val hmacKey: String) {
    private val logger = LoggerFactory.getLogger(RestController::class.java)
    private val userId = Mappers.getMapper(UserConverter::class.java)
    private val userConverter = Mappers.getMapper(UserConverter::class.java)

    suspend fun saveEmailAuthCode(randomKey: String, request: SendEmailDto.SendEmailRequest): Authentication {
        val res = authenticationRepository.save(Authentication(request.email, randomKey, request.emailType.type, LocalDateTime.now(), LocalDateTime.now(), true))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_SAVE_EMAIL_CODE)
        return res
    }

    suspend fun authenticateEmailCode(request: com.riti.data.dto.AuthenticateEmailCodeDto.AuthenticateEmailCodeRequest): Authentication {
        val res = authenticationRepository.findByUserIdAndAuthCodeAndEmailTypeAndcreatedAtAfter(request.email, request.authCode
            ,request.emailType.type, LocalDateTime.now().minusHours(1))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_AUTHENTICATE_EMAIL_CODE)
        return res
    }

    suspend fun saveUser(request: SignUpUserDto.SignUpUserRequest): User {
        val res = userRepository.save(userId.convertToEntity(request, hmacKey))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_SAVE_USER)
        return res
    }

    suspend fun getUser(userId: String): User {
        val res = userRepository.findById(userId)
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_GET_USER)
        return res
    }

    suspend fun updateUser(request: UpdateUserDto.UpdateUserRequest): User {
        val res = userRepository.save(userConverter.convertToEntity(request))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_UPDATE_USER)
        return res
    }

    suspend fun deleteUser(userId: String): User {
        val res = getUser(userId)
        try {
            userRepository.deleteById(userId)
        } catch(e: Exception) {
            throw ApiRuntimeException(ResultCode.FAIL_TO_DELETE_USER)
        }
        return res
    }

    suspend fun signInUser(request: SignInUserDto.SignInUserRequest): User {
        val res = userRepository.findByUserIdAndPassword(request.userId, Util.encryptUserPassword(request.password, hmacKey))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_SIGN_IN_USER)
        return res
    }

    suspend fun recommendUser(gender: String, ageRangeStart: Int, ageRangeEnd: Int, recommendedUserList: List<String>): Flow<User> {
        val res = userRepository.recommendUser(gender, recommendedUserList.joinToString(","))
        if(res.toList().isEmpty()) throw ApiRuntimeException(ResultCode.FAIL_TO_RECOMMEND_USER)
        return res
    }
}