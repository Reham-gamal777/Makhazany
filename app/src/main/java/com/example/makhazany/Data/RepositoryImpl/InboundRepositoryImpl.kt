package com.example.makhazany.Data.RepositoryImpl

import com.example.makhazany.Data.Local.Dao.InboundDao
import com.example.makhazany.Data.Local.Entity.InboundEntity
import com.example.makhazany.Domain.Repository.InboundRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InboundRepositoryImpl @Inject constructor(
    private val inboundDao: InboundDao
) : InboundRepository {
    override fun getInboundByItem(itemId: Int): Flow<List<InboundEntity>> {
        return inboundDao.getInbound() // Assuming we might want to filter by itemId in Dao later
    }

    override suspend fun insertInbound(inbound: InboundEntity) {
        inboundDao.insertInbound(inbound)
    }
}
