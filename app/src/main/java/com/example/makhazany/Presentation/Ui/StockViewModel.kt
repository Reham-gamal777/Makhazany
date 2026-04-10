package com.example.makhazany.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.data.local.relation.ItemWithStock
import com.example.makhazany.domain.repository.StockRepository
import com.example.makhazany.data.local.entity.ItemEntity
import com.example.makhazany.data.local.entity.StockEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    private val _stockItems = MutableStateFlow<List<ItemWithStock>>(emptyList())
    val stockItems: StateFlow<List<ItemWithStock>> = _stockItems.asStateFlow()

    private val _totalItems = MutableStateFlow(0)
    val totalItems: StateFlow<Int> = _totalItems.asStateFlow()

    private val _shortages = MutableStateFlow(0)
    val shortages: StateFlow<Int> = _shortages.asStateFlow()

    init {
        getStockData()
    }

    private fun getStockData() {
        viewModelScope.launch {
            repository.getItemsWithStock().collectLatest { list ->
                _stockItems.value = list
                _totalItems.value = list.size
                _shortages.value = list.count { (it.stock?.quantity ?: 0.0) < 10 }
            }
        }
    }

    fun addItem(item: ItemEntity, quantity: Double) {
        viewModelScope.launch {
            val id = repository.insertItem(item)
            repository.insertStock(StockEntity(itemId = id.toInt(), quantity = quantity))
        }
    }
}
