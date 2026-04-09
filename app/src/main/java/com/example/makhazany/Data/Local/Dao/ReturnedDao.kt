package com.example.makhazany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.makhazany.data.local.entity.ReturnedEntity
import com.example.makhazany.data.local.relation.ReturnedWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ReturnedDao {
    @Insert
    suspend fun insertReturned(returned: ReturnedEntity): Long

    @Transaction
    @Query("SELECT * FROM returned")
    fun getReturnedWithDetails(): Flow<List<ReturnedWithDetails>>
}
