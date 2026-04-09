package com.example.makhazany.data.repository

import com.example.makhazany.data.local.dao.OutboundDao
import com.example.makhazany.data.local.dao.OutboundDetailsDao
import com.example.makhazany.data.local.entity.OutboundDetailsEntity
import com.example.makhazany.data.local.entity.OutboundEntity
import com.example.makhazany.data.local.relation.OutboundWithDetails
import com.example.makhazany.domain.repository.OutboundRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OutboundRepositoryImpl @Inject constructor(
    private val outboundDao: OutboundDao,
    private val detailsDao: OutboundDetailsDao
) : OutboundRepository {

    override suspend fun insertOutbound(
        outbound: OutboundEntity
    ): Long {
        return outboundDao.insertOutbound(outbound)
    }

    override suspend fun insertDetails(
        details: List<OutboundDetailsEntity>
    ) {
        details.forEach {
            detailsDao.insertDetails(it)
        }
    }

    override fun getOutboundWithDetails(): Flow<List<OutboundWithDetails>> {
        return outboundDao.getOutboundWithDetails()
    }
}
