package com.example.makhazany.Domain.Repository

import com.example.makhazany.Data.Local.Entity.ItemEntity
import com.example.makhazany.Data.Local.Relation.ItemWithStock
import com.example.makhazany.Data.Local.Entity.StockEntity
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getItemsWithStock(): Flow<List<ItemWithStock>>
    suspend fun getStockByItem(itemId: Int): StockEntity?
    suspend fun insertStock(stock: StockEntity)
    suspend fun insertItem(item: ItemEntity): Long
}
