package com.example.makhazany.domain.repository

import com.example.makhazany.data.local.relation.ItemWithStock
import com.example.makhazany.data.local.entity.ItemEntity
import com.example.makhazany.data.local.entity.StockEntity
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getItemsWithStock(): Flow<List<ItemWithStock>>
    suspend fun getStockByItem(itemId: Int): StockEntity?
    suspend fun insertStock(stock: StockEntity)
    suspend fun insertItem(item: ItemEntity): Long
}
