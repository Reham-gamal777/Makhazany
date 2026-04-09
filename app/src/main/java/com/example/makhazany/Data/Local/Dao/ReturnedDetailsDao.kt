package com.example.makhazany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.makhazany.data.local.entity.ReturnedDetailsEntity

@Dao
interface ReturnedDetailsDao {
    @Insert
    suspend fun insertReturnedDetails(details: ReturnedDetailsEntity)

    @Query("SELECT * FROM returned_details WHERE returnedId = :returnedId")
    suspend fun getReturnedDetails(returnedId: Int): List<ReturnedDetailsEntity>
}
