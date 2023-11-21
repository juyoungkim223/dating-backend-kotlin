package com.riti.backendforfrontend.repository.entity

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * https://techblog.woowahan.com/12903/
 * https://stackoverflow.com/questions/70877916/spring-data-r2dbc-postgresql-not-saving-new-record-with-uuid-id
 * @Entity does not need in r2dbc
 *
 */
@Table("user")
data class User(
    // email or auto generated id (서류 인증을 통한 가입)
    @Id
    @Column("user_id")
    val userId: String,
    @Column("password")
    val password: String,
    // basic
    @Column("company")
    val company: Int,
    @Column("school")
    val school: String,
    @Column("name")
    val name: String,
    @Column("birth_date")
    val birthDate: String,
    @Column("gender")
    var gender: String,
    @Column("address")
    val address: String,
    @Column("height")
    val height: Int,
    // prefer age
    @Column("age_range_start")
    val ageRangeStart: Int,
    @Column("age_range_end")
    val ageRangeEnd: Int,
    // life style
    @Column("drinking")
    val drinking: String,
    @Column("smoking")
    val smoking: String,
    @Column("personality_type")
    val personalityType: String,
    @Column("workout")
    val workout: String,
    @Column("communication_style")
    val communicationStyle: String,
    // 관심사
    @Column("interest1")
    val interest1: String,
    @Column("interest2")
    val interest2: String,
    @Column("interest3")
    val interest3: String,
    @Column("interest4")
    val interest4: String,
    @Column("interest5")
    val interest5: String,
    // photo
    @Column("photo1")
    val photo1: String,
    @Column("photo2")
    val photo2: String,
    @Column("photo3")
    val photo3: String,
    @Column("photo4")
    val photo4: String,
    @Column("photo5")
    val photo5: String,
    @Column("photo6")
    val photo6: String,
    @Column("status")
    val status: String,
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