package com.example.makhazany.Domain.Repository

import com.example.makhazany.Data.Local.Entity.InboundEntity
import kotlinx.coroutines.flow.Flow

interface InboundRepository {
    fun getInboundByItem(itemId: Int): Flow<List<InboundEntity>>
    suspend fun insertInbound(inbound: InboundEntity)
}
