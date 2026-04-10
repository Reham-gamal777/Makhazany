package com.example.makhazany.domain.repository

import com.example.makhazany.data.local.entity.InboundEntity
import kotlinx.coroutines.flow.Flow

interface InboundRepository {
    fun getInboundByItem(itemId: Int): Flow<List<InboundEntity>>
    suspend fun insertInbound(inbound: InboundEntity)
}
