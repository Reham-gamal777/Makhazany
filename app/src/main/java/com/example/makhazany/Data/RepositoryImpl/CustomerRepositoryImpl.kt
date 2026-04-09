package com.example.makhazany.Data.RepositoryImpl

import com.example.makhazany.Data.Local.Dao.CustomerDao
import com.example.makhazany.Data.Local.Entity.CustomerEntity
import com.example.makhazany.Domain.Repository.CustomerRepository
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
