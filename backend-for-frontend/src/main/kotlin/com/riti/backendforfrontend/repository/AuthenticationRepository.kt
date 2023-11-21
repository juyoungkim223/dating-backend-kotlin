package com.riti.backendforfrontend.repository

import com.riti.backendforfrontend.repository.entity.Authentication
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


@Repository
interface AuthenticationRepository : CoroutineCrudRepository<Authentication?, String?> {
    @Query(
        """
        SELECT * FROM authentication a
        WHERE a.user_id = :id
        AND a.auth_code = :authCode
        AND a.email_type = :emailType
        AND a.created_at >= :limitTime
        ORDER BY created_at desc
        LIMIT 1
        """
    )
    suspend fun findByUserIdAndAuthCodeAndEmailTypeAndcreatedAtAfter(id: String, authCode: String, emailType: String, limitTime: LocalDateTime): Authentication?
/*
    fun findByFirstname(firstname: String?): Flux<User?>?

    @Modifying
    @Query("UPDATE person SET firstname = :firstname where lastname = :lastname")
    fun setFixedFirstnameFor(firstname: String?, lastname: String?): Mono<Int?>?

    @Query("SELECT * FROM person WHERE lastname = :#{[0]}")
    fun findByQueryWithExpression(lastname: String?): Flux<User?>?*/
}