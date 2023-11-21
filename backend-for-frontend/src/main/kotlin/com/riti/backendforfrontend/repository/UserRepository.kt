package com.riti.backendforfrontend.repository

import com.riti.backendforfrontend.repository.entity.User
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/
 */
@Repository
interface UserRepository : CoroutineCrudRepository<User?, String?> {
    suspend fun findByUserIdAndPassword(userId: String, password: String): User?

    /**
     * 나이 검색 조건 추가
     * AND a.age >= :ageRangeStart
     * AND a.age <= :ageRangeEnd
     */
    @Query(
        """
        SELECT * FROM user
        WHERE gender = :gender
        AND user_id NOT IN(:recommendedUserList)
        ORDER BY RAND() 
        LIMIT 1
        """
    )
    suspend fun recommendUser(gender: String, recommendedUserList: String): Flow<User>

}