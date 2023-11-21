package com.riti.backendforfrontend.repository

import com.riti.backendforfrontend.repository.entity.Inventory
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface InventoryRepository : CoroutineCrudRepository<Inventory?, String?> {

}