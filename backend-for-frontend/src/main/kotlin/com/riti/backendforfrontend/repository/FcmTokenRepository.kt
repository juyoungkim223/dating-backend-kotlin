package com.riti.backendforfrontend.repository

import com.riti.backendforfrontend.repository.entity.FcmToken
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
interface FcmTokenRepository : CoroutineCrudRepository<FcmToken?, String?> {
}