package com.riti.backendforfrontend.repository.entity

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

/**
 * https://techblog.woowahan.com/12903/
 * https://stackoverflow.com/questions/70877916/spring-data-r2dbc-postgresql-not-saving-new-record-with-uuid-id
 * @Entity does not need in r2dbc
 *
 */
@Table("purchase_history")
data class PurchaseHistory(
    @Id
    @Column("user_id")
    val userId: String,
    @Column("product")
    val product: String,
    @Column("price")
    val price: Float,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @Transient
    @Value("false")
    val new: Boolean
) : Persistable<String> {
    override fun getId(): String = userId
    override fun isNew(): Boolean = new

}