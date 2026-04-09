package com.example.makhazany.domain.usecase

import com.example.makhazany.domain.model.DashboardItem
import com.example.makhazany.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDashboardItemsUseCase (
    private val repository: DashboardRepository
) {
    operator fun invoke(): Flow<List<DashboardItem>> {
        return repository.getDashboardItems()
    }
}
