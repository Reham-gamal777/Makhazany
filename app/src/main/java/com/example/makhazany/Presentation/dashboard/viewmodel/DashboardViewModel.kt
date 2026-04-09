package com.example.makhazany.presentation.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.domain.usecase.GetDashboardItemsUseCase
import com.example.makhazany.presentation.dashboard.state.DashboardUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDashboardItemsUseCase: GetDashboardItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            try {
                getDashboardItemsUseCase()
                    .catch { exception ->
                        _uiState.value = DashboardUiState.Error("حدث خطأ في تحميل البيانات")
                    }
                    .collect { items ->
                        _uiState.value = DashboardUiState.Success(items)
                    }
            } catch (e: Exception) {
                _uiState.value = DashboardUiState.Error("حدث خطأ في تحميل البيانات")
            }
        }
    }
}
