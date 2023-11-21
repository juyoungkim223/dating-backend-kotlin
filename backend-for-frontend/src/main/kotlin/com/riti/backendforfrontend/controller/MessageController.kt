package com.riti.backendforfrontend.controller

import com.riti.backendforfrontend.facade.MessageFacade
import com.riti.backendforfrontend.handler.MessageSocketHandler
import com.riti.backendforfrontend.mapper.MessageConverter
import com.riti.data.dto.*
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.flow.toList
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

/**
 * 채팅
 * https://jonghoonpark.com/chatting-system-design
 * */
@RestController
@RequestMapping("/api/v1/message")
@Slf4j
class MessageController(private val messageFacade: MessageFacade
, private val messageSocketHandler: MessageSocketHandler) {
    private val logger = LoggerFactory.getLogger(MatchController::class.java)
    private val messageConverter = Mappers.getMapper(MessageConverter::class.java)

    /**
     * Get message
     */
    @Operation(summary = "Get Message", description = "Get Message")
    @GetMapping("/getMessage")
    suspend fun getMessage(request: GetMessageDto.GetMessageRequestDto): ApiResponse<GetMessageDto.GetMessageResponseDto> {
        val messages = messageFacade.getMessage(request)
        val list = ArrayList<GetMessageDto.GetMessage>()
        messages.toList().forEach{
            if(it != null) {
                list.add(messageConverter.convertToGetMessageDto(it))
            }
        }
        return ApiResponse.ok(GetMessageDto.GetMessageResponseDto(messages = list, pageNo = messages.toList().last()?.inboxId));
    }

    /**
     * Get Inbox
     */
    @Operation(summary = "Get Inbox", description = "Get Inbox")
    @GetMapping("/getInbox")
    suspend fun getMessageRoom(request: GetInboxDto.GetInboxRequestDto): ApiResponse<GetInboxDto.GetInboxResponseDto> {
        val messages = messageFacade.getInbox(request)
        val list = ArrayList<GetInboxDto.GetInbox>()
        messages.toList().forEach{
            if(it != null)
                list.add(messageConverter.convertToGetInboxDto(it))
        }
        return ApiResponse.ok(GetInboxDto.GetInboxResponseDto(list));
    }


    @Operation(summary = "Send Message To Connected user using websocket", description = "Send Message To Connected user using websocket")
    @PostMapping("/sendMessageToConnectedUser")
    suspend fun sendMessageToConnectedUser(request: SocketMessageDto.SocketMessageResponseDto): ApiResponse<Boolean> {
        messageSocketHandler.send(request)
        return ApiResponse.ok(true)
    }
}