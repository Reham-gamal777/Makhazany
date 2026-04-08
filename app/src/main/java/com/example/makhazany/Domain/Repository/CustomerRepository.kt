package com.example.makhazany.Domain.Repository

import com.example.makhazany.Data.Local.Entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCustomers(): Flow<List<CustomerEntity>>
    suspend fun insertCustomer(customer: CustomerEntity)
}
