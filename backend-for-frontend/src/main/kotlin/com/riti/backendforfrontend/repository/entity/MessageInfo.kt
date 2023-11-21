package com.riti.backendforfrontend.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * chatting message info, insert record for every receiver in group or one-one chatting
 */
@Table("message_info")
data class MessageInfo(
    @Column("message_id")
    val messageId: String,
    @Column("receiver_id")
    val receiverId: Long,
    @Column("status")
    val status: String,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {

}