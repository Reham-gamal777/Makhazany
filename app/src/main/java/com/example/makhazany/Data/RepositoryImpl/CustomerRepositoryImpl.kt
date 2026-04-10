package com.example.makhazany.data.repository

import com.example.makhazany.data.local.dao.CustomerDao
import com.example.makhazany.data.local.entity.CustomerEntity
import com.example.makhazany.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val customerDao: CustomerDao
) : CustomerRepository {
    override fun getCustomers(): Flow<List<CustomerEntity>> {
        return customerDao.getCustomers()
    }

    override suspend fun insertCustomer(customer: CustomerEntity) {
        customerDao.insertCustomer(customer)
    }
}
