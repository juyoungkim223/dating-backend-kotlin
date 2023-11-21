package com.riti.backendforfrontend.service

import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * This service creates and parses tokens.
 * It will do database operations.
 */
@Service
@Slf4j
class TokenService(
    @Value("\${jwt.key}")
    private val originKey: String,
) {
    val secretKey = Keys.hmacShaKeyFor(originKey.toByteArray(StandardCharsets.UTF_8))
    private val logger = LoggerFactory.getLogger(TokenService::class.java)

    fun parseJWT(jwt: String, userId: String): Jws<Claims> {
        val jwtObject = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(originKey.toByteArray(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(jwt)
        /**
         *  JWT subject(identify each of user) compare with request User
         */
        if(!jwtObject.body.subject.equals(userId))
            throw ApiRuntimeException(ResultCode.FAIL_AUTHENTICATE_SIGN_IN_TOKEN_BY_DIFFERENT_SUBJECT)
        logger.debug(jwtObject.body.subject)
        logger.debug(LocalDateTime.ofInstant(Instant.ofEpochMilli(jwtObject.body.issuedAt.time), ZoneId.of("Asia/Seoul")).toString())
        logger.debug(LocalDateTime.ofInstant(Instant.ofEpochMilli(jwtObject.body.expiration.time), ZoneId.of("Asia/Seoul")).toString())
        return jwtObject
    }

    fun createJWT(userId: String): String {
        val jwt = Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toInstant()))
            .setExpiration(Date.from(LocalDateTime.now().plusYears(1).atZone(ZoneId.of("Asia/Seoul")).toInstant()))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
        return jwt;
    }
}