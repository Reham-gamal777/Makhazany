package com.example.makhazany.features.exports.presentation

import com.example.makhazany.features.exports.domain.entity.ExportInvoice
import com.example.makhazany.features.exports.domain.entity.ExportsSummary

data class ExportsUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val items: List<ExportInvoice> = emptyList(),
    val summary: ExportsSummary? = null,
    val errorMessage: String? = null,
    val page: Int = 1,
    val canLoadMore: Boolean = false
)
