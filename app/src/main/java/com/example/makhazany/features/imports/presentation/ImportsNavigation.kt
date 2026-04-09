package com.example.makhazany.features.imports.presentation

import androidx.compose.runtime.*
import com.example.makhazany.features.imports.domain.entity.ImportInvoice

sealed class ImportsNavigationState {
    object List : ImportsNavigationState()
    object Add : ImportsNavigationState()
    data class Details(val importInvoice: ImportInvoice) : ImportsNavigationState()
}

@Composable
fun ImportsNavigation(
    onBackToMain: () -> Unit
) {
    var navigationState by remember { mutableStateOf<ImportsNavigationState>(ImportsNavigationState.List) }

    when (val state = navigationState) {
        is ImportsNavigationState.List -> {
            ImportsRoute(
                onBackClick = onBackToMain,
                onAddImportClick = {
                    navigationState = ImportsNavigationState.Add
                },
                onItemClick = { importInvoice ->
                    navigationState = ImportsNavigationState.Details(importInvoice)
                }
            )
        }
        is ImportsNavigationState.Add -> {
            AddImportScreen(
                onBackClick = {
                    navigationState = ImportsNavigationState.List
                }
            )
        }
        is ImportsNavigationState.Details -> {
            ImportDetailsScreen(
                importInvoice = state.importInvoice,
                onBackClick = {
                    navigationState = ImportsNavigationState.List
                }
            )
        }
    }
}
