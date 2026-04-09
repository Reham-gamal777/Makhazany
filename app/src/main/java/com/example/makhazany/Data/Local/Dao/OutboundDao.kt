package com.example.makhazany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.makhazany.data.local.relation.OutboundWithDetails
import com.example.makhazany.data.local.entity.OutboundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OutboundDao {
    @Insert
    suspend fun insertOutbound(outbound: OutboundEntity): Long

    @Transaction
    @Query("SELECT * FROM outbound")
    fun getOutboundWithDetails(): Flow<List<OutboundWithDetails>>
}
