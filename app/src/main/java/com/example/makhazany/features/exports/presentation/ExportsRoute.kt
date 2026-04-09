package com.example.makhazany.features.exports.presentation

import androidx.compose.runtime.Composable
import com.example.makhazany.features.exports.domain.entity.ExportInvoice

@Composable
fun ExportsRoute(
    onBackClick: () -> Unit,
    onAddExportClick: () -> Unit,
    onExportClick: (ExportInvoice) -> Unit
) {
    ExportsScreen(
        onBackClick = onBackClick,
        onAddExportClick = onAddExportClick,
        onExportClick = onExportClick
    )
}
