package com.example.makhazany.data.repository

import com.example.makhazany.data.local.dao.InboundDao
import com.example.makhazany.data.local.entity.InboundEntity
import com.example.makhazany.domain.repository.InboundRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InboundRepositoryImpl @Inject constructor(
    private val inboundDao: InboundDao
) : InboundRepository {
    override fun getInboundByItem(itemId: Int): Flow<List<InboundEntity>> {
        return inboundDao.getInbound()
    }

    override suspend fun insertInbound(inbound: InboundEntity) {
        inboundDao.insertInbound(inbound)
    }
}
