package com.riti.backendforfrontend.repository.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * chatting message inbox
 */
@Table("inbox")
data class Inbox(
    @Column("last_message")
    val senderId: String,
    @Column("last_sent_id")
    val lastSentId: String,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {

}