package com.example.makhazany.features.imports.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makhazany.core.util.Result
import com.example.makhazany.features.imports.domain.usecase.GetImportsSummaryUseCase
import com.example.makhazany.features.imports.domain.usecase.GetImportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportsViewModel @Inject constructor(
    private val getImportsUseCase: GetImportsUseCase,
    private val getImportsSummaryUseCase: GetImportsSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImportsUiState())
    val uiState: StateFlow<ImportsUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadData()
    }

    fun loadData() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        loadSummary()
        loadImports()
    }

    private fun loadSummary() {
        viewModelScope.launch {
            getImportsSummaryUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(summary = result.data) }
                    }
                    is Result.Failure -> {
                        // For simplicity, we just set the error if it's the first load
                        _uiState.update { it.copy(errorMessage = "Failed to load summary") }
                    }
                }
            }
        }
    }

    private fun loadImports(query: String? = null) {
        viewModelScope.launch {
            getImportsUseCase(query).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(items = result.data, isLoading = false) }
                    }
                    is Result.Failure -> {
                        _uiState.update { it.copy(isLoading = false, errorMessage = "Failed to load imports") }
                    }
                }
            }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            loadImports(newQuery)
        }
    }
}
