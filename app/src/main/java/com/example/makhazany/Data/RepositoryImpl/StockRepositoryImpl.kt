package com.example.makhazany.data.repository

import com.example.makhazany.data.local.dao.ItemDao
import com.example.makhazany.data.local.dao.StockDao
import com.example.makhazany.data.local.relation.ItemWithStock
import com.example.makhazany.domain.repository.StockRepository
import com.example.makhazany.data.local.entity.ItemEntity
import com.example.makhazany.data.local.entity.StockEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao,
    private val stockDao: StockDao
) : StockRepository {
    override fun getItemsWithStock(): Flow<List<ItemWithStock>> {
        return itemDao.getAllItems()
    }

    override suspend fun getStockByItem(itemId: Int): StockEntity? {
        return stockDao.getStockByItem(itemId)
    }

    override suspend fun insertStock(stock: StockEntity) {
        stockDao.insertStock(stock)
    }

    override suspend fun insertItem(item: ItemEntity): Long {
        return itemDao.insertItem(item)
    }
}
