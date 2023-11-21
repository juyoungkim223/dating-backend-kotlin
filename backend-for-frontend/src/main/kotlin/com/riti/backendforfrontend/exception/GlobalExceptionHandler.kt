package com.riti.backendforfrontend.exception

import com.riti.data.dto.ApiResponse
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.function.server.*


@Component
@Order(-2)
class GlobalExceptionHandler(
    errorAttributes: ErrorAttributes,
    properties: WebProperties,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(
    errorAttributes, properties.resources, applicationContext
) {
    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
        super.setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(
        errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse?>? {
        return RouterFunctions.route(RequestPredicates.all(), getBindingErrorResult)
    }

    val getBindingErrorResult = HandlerFunction<ServerResponse> { request ->
        when (val throwable = super.getError(request)) {

            is WebExchangeBindException -> {
                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(
                        ApiResponse(null
                            , ResultCode.INTERNAL_SERVER_ERROR.resultCode
                            , throwable.message.toString())
                            //, throwable.bindingResult.allErrors.map { m -> m.defaultMessage }.toList().toString())
                    )
            }
            is ApiRuntimeException -> {
                val resultCode = ResultCode.of(throwable.message.toString())
                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(
                        ApiResponse(null
                            , resultCode.resultCode
                            , resultCode.resultMessage
                    ))

            }
            else -> {
                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(
                        ApiResponse(null
                            , ResultCode.INTERNAL_SERVER_ERROR.resultCode
                            , throwable.message.toString())
                    )
            }
        }
    }
}