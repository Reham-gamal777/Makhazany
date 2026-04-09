package com.example.makhazany.features.imports.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makhazany.features.imports.domain.entity.ImportInvoice

@Composable
fun ImportsRoute(
    onBackClick: () -> Unit,
    onAddImportClick: () -> Unit,
    onItemClick: (ImportInvoice) -> Unit,
    viewModel: ImportsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    ImportsScreen(
        state = state,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onAddImportClick = onAddImportClick,
        onItemClick = onItemClick,
        onBackClick = onBackClick,
        onRefresh = viewModel::loadData
    )
}
