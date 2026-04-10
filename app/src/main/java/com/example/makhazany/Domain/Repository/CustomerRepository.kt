package com.example.makhazany.domain.repository

import com.example.makhazany.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCustomers(): Flow<List<CustomerEntity>>
    suspend fun insertCustomer(customer: CustomerEntity)
}
