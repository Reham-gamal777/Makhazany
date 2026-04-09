package com.example.makhazany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.makhazany.data.local.entity.InboundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InboundDao {
    @Insert
    suspend fun insertInbound(inbound: InboundEntity)

    @Query("SELECT * FROM inbound")
    fun getInbound(): Flow<List<InboundEntity>>
}
