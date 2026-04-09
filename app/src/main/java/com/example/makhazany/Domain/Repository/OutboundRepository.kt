package com.example.makhazany.domain.repository

import com.example.makhazany.data.local.entity.OutboundDetailsEntity
import com.example.makhazany.data.local.entity.OutboundEntity
import com.example.makhazany.data.local.relation.OutboundWithDetails
import kotlinx.coroutines.flow.Flow

interface OutboundRepository {
    suspend fun insertOutbound(outbound: OutboundEntity): Long
    suspend fun insertDetails(details: List<OutboundDetailsEntity>)
    fun getOutboundWithDetails(): Flow<List<OutboundWithDetails>>
}
