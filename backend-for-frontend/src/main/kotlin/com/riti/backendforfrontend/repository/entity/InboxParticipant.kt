package com.riti.backendforfrontend.repository.entity

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * chatting message inbox participants
 */
@Table("inbox_participant")
data class InboxParticipant(
    @Column("user_id")
    val userId: String,
    @Column("inbox_id")
    val inboxId: String,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
}