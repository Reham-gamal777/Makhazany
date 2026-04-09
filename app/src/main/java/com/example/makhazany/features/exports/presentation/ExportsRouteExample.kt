package com.example.makhazany.features.exports.presentation

import androidx.compose.runtime.Composable


@Composable
fun ExportsRouteWithNavigationExample(
    onBackToMain: () -> Unit
) {
    ExportsNavigation(
        onBackToMain = onBackToMain
    )
}
