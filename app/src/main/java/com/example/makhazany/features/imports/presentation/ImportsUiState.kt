package com.example.makhazany.features.imports.presentation

import com.example.makhazany.features.imports.domain.entity.ImportInvoice
import com.example.makhazany.features.imports.domain.entity.ImportsSummary

data class ImportsUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val items: List<ImportInvoice> = emptyList(),
    val summary: ImportsSummary? = null,
    val errorMessage: String? = null
)
