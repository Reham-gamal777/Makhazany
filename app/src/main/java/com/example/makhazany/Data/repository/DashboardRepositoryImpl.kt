package com.example.makhazany.data.repository

import com.example.makhazany.data.local.LocalDataSource
import com.example.makhazany.data.mapper.toDomainModel
import com.example.makhazany.domain.model.DashboardItem
import com.example.makhazany.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DashboardRepositoryImpl(
    private val localDataSource: LocalDataSource
) : DashboardRepository {
    override fun getDashboardItems(): Flow<List<DashboardItem>> {
        return localDataSource.fetchDashboardItems().map { list ->
            list.map { it.toDomainModel() }
        }
    }
}
