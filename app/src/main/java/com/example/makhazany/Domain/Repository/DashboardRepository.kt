package com.example.makhazany.domain.repository

import com.example.makhazany.domain.model.DashboardItem
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getDashboardItems(): Flow<List<DashboardItem>>
}
