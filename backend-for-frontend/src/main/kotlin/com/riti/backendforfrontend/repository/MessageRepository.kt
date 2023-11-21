package com.riti.backendforfrontend.repository

import com.riti.backendforfrontend.repository.entity.Inbox
import com.riti.backendforfrontend.repository.entity.Message
import com.riti.data.dto.UpdateMessageInfoDto
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface MessageRepository : CoroutineCrudRepository<Message?, Long?> {
    /**
     * fetch chatting message
     */
    @Query(
        """
        SELECT * 
        FROM message
        WHERE inbox_id = :inboxId
        ORDER BY created_at DESC, id DESC
        LIMIT 20
        """
    )
    suspend fun getMessage(inboxId: Long): Flow<Message>

    /**
     * fetch chatting message based on cursor pagination
     */
    @Query(
        """
        SELECT * 
        FROM message
        WHERE inbox_id = :inboxId 
        AND (created_at < select created_at from message where id = :cursor
            OR id < :cursor AND created_at = select created_at from message where id = :cursor)
        ORDER BY created_at DESC, id DESC
        LIMIT 20
        """
    )
    suspend fun getMessage(inboxId: Long, cursor: Long): Flow<Message>

    /**
     * update message_info status
     */
    @Modifying
    @Query(
        """
        UPDATE message_info
        SET status = :status
        WHERE id = :messageId
        AND receiver_id = :receiverId
        """
    )
    suspend fun updateMessageStatus(request: UpdateMessageInfoDto.UpdateMessageInfoRequestDto): Message?

    @Query(
        """
        SELECT uuid() as id
        FROM  INFORMATION_SCHEMA.TABLES
        WHERE TABLE_SCHEMA = "dating"
        AND TABLE_NAME = "message"
        """
    )
    suspend fun getId(): Message?

}