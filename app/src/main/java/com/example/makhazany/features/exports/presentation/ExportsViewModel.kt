package com.example.makhazany.features.exports.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.core.util.Resource
import com.example.makhazany.features.exports.domain.usecase.GetExportsSummaryUseCase
import com.example.makhazany.features.exports.domain.usecase.GetExportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExportsViewModel @Inject constructor(
    private val getExportsUseCase: GetExportsUseCase,
    private val getExportsSummaryUseCase: GetExportsSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExportsUiState())
    val uiState: StateFlow<ExportsUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null
    private val LIMIT = 10

    init {
        loadSummary()
        loadExports()
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(query = newQuery, page = 1, items = emptyList()) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400) // Debounce
            loadExports()
        }
    }

    private fun loadSummary() {
        getExportsSummaryUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> _uiState.update { it.copy(summary = resource.data) }
                is Resource.Error -> _uiState.update { it.copy(errorMessage = resource.message) }
                is Resource.Loading -> {} 
            }
        }.launchIn(viewModelScope)
    }

    fun loadExports() {
        val currentState = _uiState.value
        getExportsUseCase(currentState.query, currentState.page, LIMIT).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val newItems = resource.data?.items ?: emptyList()
                    val totalCount = resource.data?.totalCount ?: 0
                    _uiState.update { 
                        it.copy(
                            items = if (it.page == 1) newItems else it.items + newItems,
                            isLoading = false,
                            canLoadMore = it.items.size + newItems.size < totalCount
                        )
                    }
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, errorMessage = resource.message) }
                is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }

    fun loadMore() {
        if (!_uiState.value.isLoading && _uiState.value.canLoadMore) {
            _uiState.update { it.copy(page = it.page + 1) }
            loadExports()
        }
    }
}
