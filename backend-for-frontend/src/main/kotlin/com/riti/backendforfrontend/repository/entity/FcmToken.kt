package com.riti.backendforfrontend.repository.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * chatting message
 * erd 참고
 * - https://stackoverflow.com/questions/35310283/how-to-model-messages-exchanged-between-users-er-diagram
 * - https://vertabelo.com/blog/database-model-for-a-messaging-system/
 */
@Table("fcm_token")
data class FcmToken(
    @Id
    @Column("user_id")
    val userId: String,
    @Column("fcm_token")
    val fcmToken: String,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Transient
    @Value("false")
    var new: Boolean
) : Persistable<String> {
    override fun getId(): String = userId
    override fun isNew(): Boolean = new

}