package com.riti.backendforfrontend.service

import com.riti.backendforfrontend.config.KafkaConfig
import com.riti.backendforfrontend.config.ReactiveRedisConfig
import com.riti.backendforfrontend.mapper.MessageConverter
import com.riti.backendforfrontend.repository.InboxRepository
import com.riti.backendforfrontend.repository.MessageRepository
import com.riti.backendforfrontend.repository.entity.Inbox
import com.riti.backendforfrontend.repository.entity.Message
import com.riti.data.dto.*
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import com.riti.data.util.GsonUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.producer.ProducerRecord
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kafka.receiver.ReceiverRecord
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import java.net.InetAddress

@Service
@Slf4j
class MessageService(val messageRepository: MessageRepository
, val inboxRepository: InboxRepository
, val redisTemplate: ReactiveRedisTemplate<String, String>
, val kafkaConfig: KafkaConfig) {
    @Value("\${message.topic}")
    private val messageTopic: String? = null
    @Value("\${server.port}")
    private val serverPort: String? = null
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val messageConverter = Mappers.getMapper(MessageConverter::class.java)
    suspend fun getMessage(request: GetMessageDto.GetMessageRequestDto): Flow<Message?> {
        val res: Flow<Message?> = if(request.cursor == null) {
            messageRepository.getMessage(request.inboxId)
        } else {
            messageRepository.getMessage(request.inboxId, request.cursor!!)
        }
        if(res.toList().isEmpty()) throw ApiRuntimeException(ResultCode.FAIL_TO_GET_MESSAGE)
        return res
    }
    suspend fun getInbox(request: GetInboxDto.GetInboxRequestDto): Flow<Inbox?> {
        val res = if(request.cursor == null) inboxRepository.getInbox(request.userId, 0)
        else inboxRepository.getInbox(request.userId, request.cursor!!)
        if(res.toList().isEmpty()) throw ApiRuntimeException(ResultCode.FAIL_TO_GET_MESSAGE_ROOM)
        return res
    }

    suspend fun updateMessageStatus(request: UpdateMessageInfoDto.UpdateMessageInfoRequestDto): Boolean {
        val res = messageRepository.updateMessageStatus(request)
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_UPDATE_MESSAGE_STATUS)
        return true
    }

    suspend fun saveMessage(request: SaveMessageDto.SaveMessageRequest): Boolean {
        val res = messageRepository.save(messageConverter.convertToEntity(request))
        res ?: throw ApiRuntimeException(ResultCode.FAIL_TO_SAVE_MESSAGE)
        return true
    }

    fun sendMessageToKafka(request: SocketMessageDto.SocketMessageRequestDto): Flux<SenderResult<Nothing?>> {
        val json = GsonUtil.toJson(request)
        // Set topic name and send to topic
        return kafkaConfig.createKafkaSender()
            .send(Mono.just(SenderRecord.create(ProducerRecord<String, String>(messageTopic, json), null))
                .doOnError { err -> log.error("Kafka send error: {}", err.message) }
            )
    }
    /**
    *https://github.com/daggerok/reactive-kafka/blob/09ae01154be6feac6e9a91eb0444f6a2bca5130b/src/main/kotlin/com/github/daggerok/ReactiveKafkaApp.kt#L66
     */
    fun receiveMessageFromKafka(): Flux<ReceiverRecord<String, String>> {
        return kafkaConfig.createKafkaReceiver()
            .receive()
    }

    fun saveConnectedServer(httpHeaderUserId: String): Mono<Boolean> {
        return redisTemplate.opsForValue().set(httpHeaderUserId, InetAddress.getLoopbackAddress().hostAddress + ":" + serverPort)
    }

    fun getConnectedServer(userId: String): Mono<String> {
        return redisTemplate.opsForValue().get(userId)
    }
}
