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
interface InboxRepository : CoroutineCrudRepository<Inbox?, Long?> {
    /**
     * fetch inbox
     */
    @Query(
        """
        select *
        from inbox
        where id = (
            SELECT inbox_id 
            FROM inbox_participant
            WHERE user_id = :userId
        ) 
        ORDER BY updated_at DESC, id DESC
        LIMIT 20, :offset -- offset ~ 20
        """
    )
    suspend fun getInbox(userId: String, offset: Long): Flow<Inbox?>

}