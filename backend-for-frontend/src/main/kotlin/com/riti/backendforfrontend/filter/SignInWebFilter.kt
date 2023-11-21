package com.riti.backendforfrontend.filter

import com.riti.backendforfrontend.service.TokenService
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import org.springframework.web.util.pattern.PathPattern
import org.springframework.web.util.pattern.PathPatternParser
import reactor.core.publisher.Mono


/**
 *
 * WebFlux provides two types of filters: WebFilters and HandlerFilterFunctions.
 * The main difference between them is that WebFilter implementations work for all endpoints and HandlerFilterFunction implementations will only work for Router-based ones.
 * So if you use Router based endpoints you can use either of them. But if you use annotation based (like @RequestMapping/@GetMapping) you can only make use of WebFilter.
 * Here is a small example https://stackoverflow.com/questions/71794064/webfilter-do-subscribe-in-filter-logic/71860184#71860184
 * https://brunch.co.kr/@jinyoungchoi95/1
 */
@Component
class SignInWebFilter(val tokenService: TokenService): WebFilter {
    private val pathPatternList: List<PathPattern> =
        arrayListOf(PathPatternParser().parse("/api/v1/user")
        , PathPatternParser().parse("/api/v1/match/**")
        , PathPatternParser().parse("/api/v1/notification/**")
        , PathPatternParser().parse("/api/v1/purchase/**")
        //, PathPatternParser().parse("/api/v1/message/**")
        , PathPatternParser().parse("/api/v1/ws/message/**")
        )

    /**
     * Authorization
     * Type: Http header JWT 전송
     */
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val path = exchange.request.path
        if(pathPatternList.stream().anyMatch { it.matches(path.pathWithinApplication()) }) {
            //val token = exchange.request.headers.get(HttpHeaders.AUTHORIZATION)?.get(0)?.replace("Bearer", "")
            val token = exchange.request.headers.get("dating-be-auth-token")?.get(0)
                ?: throw ApiRuntimeException(ResultCode.FAIL_AUTHENTICATE_SIGN_IN_TOKEN)
            val userId = exchange.request.headers.get("user-id")?.get(0)
                ?: throw ApiRuntimeException(ResultCode.FAIL_AUTHENTICATE_SIGN_IN_TOKEN)

            try {
                tokenService.parseJWT(token, userId)
            } catch (e: ExpiredJwtException) {
                throw ApiRuntimeException(ResultCode.FAIL_AUTHENTICATE_SIGN_IN_TOKEN_BY_EXPIRE_TIME)
            } catch (e: Exception) {
                throw ApiRuntimeException(ResultCode.FAIL_AUTHENTICATE_SIGN_IN_TOKEN)
            }
        }
        return chain.filter(exchange);
    }

}