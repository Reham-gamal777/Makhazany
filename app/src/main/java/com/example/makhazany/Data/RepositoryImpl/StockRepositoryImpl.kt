package com.example.makhazany.Data.RepositoryImpl

import com.example.makhazany.Data.Local.Dao.ItemDao
import com.example.makhazany.Data.Local.Dao.StockDao
import com.example.makhazany.Data.Local.Relation.ItemWithStock
import com.example.makhazany.Domain.Repository.StockRepository
import com.example.makhazany.Data.Local.Entity.ItemEntity
import com.example.makhazany.Data.Local.Entity.StockEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao,
    private val stockDao: StockDao
) : StockRepository {
    override fun getItemsWithStock(): Flow<List<ItemWithStock>> {
        return itemDao.getItemsWithStock()
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
