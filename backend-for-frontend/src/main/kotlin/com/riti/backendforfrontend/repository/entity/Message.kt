package com.riti.backendforfrontend.repository.entity

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * chatting message
 */
@Table("message")
data class Message(
    @Column("user_id")
    val userId: String,
    @Column("inbox_id")
    val inboxId: String,
    @Column("content")
    val content: String,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
) {

}