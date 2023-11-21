package com.riti.backendforfrontend.repository.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * in app 결제 보상 entity
 */
@Table("inventory")
data class Inventory(
    @Id
    @Column("user_id")
    val userId: String,
    @Column("gold")
    var gold: Int,
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