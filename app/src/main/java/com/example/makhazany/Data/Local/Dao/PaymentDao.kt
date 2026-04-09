package com.example.makhazany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.makhazany.data.local.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

    @Insert
    suspend fun insertPayment(
        payment: PaymentEntity
    )

    @Query("""
        SELECT * FROM payments
        WHERE customerId = :customerId
    """)
    fun getPaymentsByCustomer(
        customerId: Int
    ): Flow<List<PaymentEntity>>
}
