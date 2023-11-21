package com.riti.backendforfrontend.handler

import com.riti.backendforfrontend.external.MessageApiClient
import com.riti.backendforfrontend.facade.MessageFacade
import com.riti.backendforfrontend.facade.NotificationFacade
import com.riti.backendforfrontend.mapper.MessageConverter
import com.riti.data.dto.ApiResponse
import com.riti.data.dto.SocketMessageDto
import com.riti.data.enums.MessageTypeEnum
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import com.riti.data.util.GsonUtil
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.reactor.mono
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


/**
 *
 * https://github.com/Meet-Coder-Study/book-system-design-interview
 * https://github.com/zupzup/kotlin-example-websockets/blob/main/src/main/kotlin/org/zupzup/kotlinwebfluxdemo/WebsocketHandler.kt
 * web socket test chrome extension
 * https://chrome.google.com/webstore/detail/simple-websocket-client/pfdhoblngboilpfeibdedpjgfnlcodoo/related
 * https://docs.spring.io/spring-framework/reference/web/webflux-websocket.html#webflux-websocket-server
 * https://docs.spring.io/spring-framework/reference/web/webflux-websocket.html#webflux-websocket-server-handler
 * https://docs.spring.io/spring-framework/reference/rsocket.html
 * https://stackoverflow.com/questions/53812515/webflux-websocketclient-how-to-send-multiple-requests-in-same-sessiondesign-cl
 */
@Component
class MessageSocketHandler(val messageFacade: MessageFacade
                           , val notificationFacade: NotificationFacade
                           , val messageApiClient: MessageApiClient) : WebSocketHandler{
    private val messageConverter = Mappers.getMapper(MessageConverter::class.java)
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val sessionMap = mutableMapOf<String, WebSocketSession>()

    /**
     * this method run at
     * 1. session connect
     * 2. session receive
     */

    override fun handle(session: WebSocketSession): Mono<Void> {
        // get user id from http header
        val httpHeaderUserId = session.handshakeInfo.headers.get("user-id")?.get(0)!!

        sessionMap.put(httpHeaderUserId, session)
        messageFacade.saveConnectedServer(httpHeaderUserId).subscribe()
        return session.receive()
            .map {
                GsonUtil.fromJson(it.payloadAsText, SocketMessageDto.SocketMessageRequestDto::class.java)
            }
            .filter {
                dto ->
                dto.messageType != MessageTypeEnum.NONE
                && httpHeaderUserId == dto.senderId
            }
            .flatMap {
                message -> messageFacade.sendMessageToKafka(message)
                .flatMap {
                    senderResult ->
                    session.send(Mono.just(session.textMessage(GsonUtil.toJson(ApiResponse.ok(message)))))
                }
            }
            .onErrorResume { err ->
                log.error("WebSocket receive error: {}, message: {}", ResultCode.of(err.message.toString()), err.message)
                Mono.error(ApiRuntimeException(ResultCode.of(err.message.toString())))
            }
            .then()
    }


    /**
     * fetch kafka data will be sent to connected websocket session
     * https://stackoverflow.com/questions/70532032/websocketsession-send-does-not-do-anything
     * reactive can not use @KafkaListener is blocking, calling kafka receive method execute auto-receive
     * Note: subscribe event fired!
     * 1. get message that have to send from kafka
     * 2. processing of message
     * 3. send ack to kafka
     * 4. send http call to connected websocket to user
     */
    @PostConstruct
    fun receiveDataFromKafka(): Mono<Void> {
        return messageFacade.receiveMessageFromKafka()
            .subscribe { receiveRecord ->
                // TODO get connected websocket server to user
                val socketReqDto = GsonUtil.fromJson(receiveRecord.value(), SocketMessageDto.SocketMessageRequestDto::class.java)
                messageFacade.getConnectedServer(socketReqDto.receiverId).subscribe { connectedServer ->
                    if (socketReqDto.messageType == MessageTypeEnum.SEND) {
                        val saveDto = messageConverter.covertToSaveMessageRequest(socketReqDto)
                        mono {
                            val saveResult = messageFacade.saveMessage(saveDto)
                            if (saveResult) {
                                val socketResDto = messageConverter.covertToSocketResponseDto(socketReqDto)
                                receiveRecord.receiverOffset().acknowledge()
                                messageApiClient.sendMessageToConnectedUser(socketResDto, connectedServer)
                                notificationFacade.sendFcm(saveDto)
                            }
                        }.subscribe()
                    } else if (socketReqDto.messageType == MessageTypeEnum.READ) {
                        val updateDto = messageConverter.covertToUpdateMessageInfoRequestDto(socketReqDto)
                        mono {
                            if (messageFacade.updateMessageStatus(updateDto)) {
                                val socketResDto = messageConverter.covertToSocketResponseDto(socketReqDto)
                                receiveRecord.receiverOffset().acknowledge()
                                messageApiClient.sendMessageToConnectedUser(socketResDto, connectedServer)
                            }
                        }.subscribe()
                    } else {
                        Mono.just(Unit)
                    }
                }
            }
            .toMono()
            .onErrorResume { err ->
                log.error("Kafka receive error: {}, message: {}", ResultCode.of(err.message.toString()), err.message)
                Mono.error(ApiRuntimeException(ResultCode.of(err.message.toString())))
            }
            .then()
    }

    suspend fun send(request: SocketMessageDto.SocketMessageResponseDto): Mono<Void> {
        return sessionMap.get(request.receiverId)!!.send(Mono.just(sessionMap.get(request.receiverId)!!.textMessage(GsonUtil.toJson(ApiResponse.ok(request)))))
    }
}