package com.example.makhazany.presentation.dashboard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.makhazany.core.theme.AppDimens
import com.example.makhazany.presentation.dashboard.state.DashboardUiState
import com.example.makhazany.presentation.dashboard.ui.components.DashboardCard
import com.example.makhazany.presentation.dashboard.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onNavigateToExports: () -> Unit,
    onNavigateToImports: () -> Unit,
    onNavigateToStock: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("لوحة التحكم", style = MaterialTheme.typography.titleLarge) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (val state = uiState) {
                    is DashboardUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is DashboardUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(AppDimens.paddingMedium),
                            verticalArrangement = Arrangement.spacedBy(AppDimens.paddingMedium)
                        ) {
                            items(state.items, key = { it.id }) { item ->
                                DashboardCard(
                                    item = item,
                                    onClick = when (item.id) {
                                        1 -> onNavigateToExports
                                        2 -> onNavigateToImports
                                        4 -> onNavigateToStock
                                        else -> null
                                    }
                                )
                            }
                        }
                    }
                    is DashboardUiState.Error -> {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = state.message, color = MaterialTheme.colorScheme.error)
                            Button(onClick = { viewModel.loadDashboardData() }) {
                                Text("إعادة المحاولة")
                            }
                        }
                    }
                }
            }
        }
    }
}
