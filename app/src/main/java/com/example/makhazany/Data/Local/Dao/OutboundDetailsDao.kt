package com.example.makhazany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.makhazany.data.local.entity.OutboundDetailsEntity

@Dao
interface OutboundDetailsDao {
    @Insert
    suspend fun insertDetails(details: OutboundDetailsEntity)

    @Query("SELECT * FROM outbound_details WHERE outboundId = :outboundId")
    suspend fun getDetailsByOutbound(outboundId: Int): List<OutboundDetailsEntity>
}
