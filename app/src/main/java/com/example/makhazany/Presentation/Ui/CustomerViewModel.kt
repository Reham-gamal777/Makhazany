package com.example.makhazany.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.domain.repository.CustomerRepository
import com.example.makhazany.data.local.entity.CustomerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    private val _customers = MutableStateFlow<List<CustomerEntity>>(emptyList())
    val customers: StateFlow<List<CustomerEntity>> = _customers.asStateFlow()

    private val _totalDebt = MutableStateFlow(0.0)
    val totalDebt: StateFlow<Double> = _totalDebt.asStateFlow()

    init {
        getCustomers()
    }

    private fun getCustomers() {
        viewModelScope.launch {
            repository.getCustomers().collectLatest { customerList ->
                _customers.value = customerList
                _totalDebt.value = customerList.sumOf { it.customerDebt }
            }
        }
    }

    fun addCustomer(customer: CustomerEntity) {
        viewModelScope.launch {
            repository.insertCustomer(customer)
        }
    }
}
