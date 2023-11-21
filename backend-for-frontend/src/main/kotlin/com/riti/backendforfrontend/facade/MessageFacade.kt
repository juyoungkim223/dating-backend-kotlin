package com.riti.backendforfrontend.facade

import com.riti.backendforfrontend.repository.entity.Inbox
import com.riti.backendforfrontend.repository.entity.Message
import com.riti.backendforfrontend.service.MessageService
import com.riti.data.dto.*
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kafka.receiver.ReceiverRecord
import reactor.kafka.sender.SenderResult

@Component
class MessageFacade(private val messageService: MessageService) {

    suspend fun getMessage(request: GetMessageDto.GetMessageRequestDto): Flow<Message?> {
        return messageService.getMessage(request)
    }
    suspend fun getInbox(request: GetInboxDto.GetInboxRequestDto): Flow<Inbox?> {
        return messageService.getInbox(request)
    }
    suspend fun updateMessageStatus(request: UpdateMessageInfoDto.UpdateMessageInfoRequestDto): Boolean {
        return messageService.updateMessageStatus(request)
    }

    suspend fun saveMessage(request: SaveMessageDto.SaveMessageRequest): Boolean {
        return messageService.saveMessage(request)
    }

    fun sendMessageToKafka(request: SocketMessageDto.SocketMessageRequestDto): Flux<SenderResult<Nothing?>> {
        return messageService.sendMessageToKafka(request)
    }

    fun receiveMessageFromKafka(): Flux<ReceiverRecord<String, String>> {
        return messageService.receiveMessageFromKafka()
    }

    fun saveConnectedServer(userId: String): Mono<Boolean> {
        return messageService.saveConnectedServer(userId)
    }

    fun getConnectedServer(userId: String): Mono<String> {
        return messageService.getConnectedServer(userId)
    }

}
