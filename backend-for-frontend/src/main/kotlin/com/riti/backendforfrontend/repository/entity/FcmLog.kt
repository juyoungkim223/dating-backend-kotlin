package com.riti.backendforfrontend.repository.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * fcm log
 * https://zuminternet.github.io/FCM-PUSH/
 */
@Table("fcm_log")
data class FcmLog(
    @Column("id")
    val id: String?,
    @Column("user_id")
    val userId: String,
    @Column("fcm_token")
    val fcmToken: String,
    @Column("title")
    val title: String,
    @Column("content")
    val content: String,
    @Column("result_code")
    val resultCode: String,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Transient
    @Value("false")
    var new: Boolean
    )  {

}