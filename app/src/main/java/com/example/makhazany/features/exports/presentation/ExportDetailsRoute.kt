package com.example.makhazany.features.exports.presentation

import androidx.compose.runtime.Composable
import com.example.makhazany.features.exports.domain.entity.ExportInvoice

@Composable
fun ExportDetailsRoute(
    export: ExportInvoice,
    onBackClick: () -> Unit
) {
    ExportDetailsScreen(
        export = export,
        onBackClick = onBackClick
    )
}
