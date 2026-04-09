package com.example.makhazany.presentation.dashboard.state

import com.example.makhazany.domain.model.DashboardItem

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val items: List<DashboardItem>) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}
