package com.example.makhazany.features.exports.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.makhazany.features.exports.domain.entity.ExportInvoice

sealed class ExportsNavigationState {
    object List : ExportsNavigationState()
    data class Details(val export: ExportInvoice) : ExportsNavigationState()
}

@Composable
fun ExportsNavigation(
    onBackToMain: () -> Unit
) {
    var navigationState by remember { mutableStateOf<ExportsNavigationState>(ExportsNavigationState.List) }

    when (val state = navigationState) {
        is ExportsNavigationState.List -> {
            ExportsRoute(
                onBackClick = onBackToMain,
                onAddExportClick = { /* TODO: Navigate to add export screen */ },
                onExportClick = { export ->
                    navigationState = ExportsNavigationState.Details(export)
                }
            )
        }
        is ExportsNavigationState.Details -> {
            ExportDetailsRoute(
                export = state.export,
                onBackClick = {
                    navigationState = ExportsNavigationState.List
                }
            )
        }
    }
}
