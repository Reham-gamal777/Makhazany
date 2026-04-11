package com.example.makhazany

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makhazany.core.theme.DashboardTheme
import com.example.makhazany.data.local.LocalDataSource
import com.example.makhazany.data.repository.DashboardRepositoryImpl
import com.example.makhazany.domain.usecase.GetDashboardItemsUseCase
import com.example.makhazany.features.exports.presentation.ExportsRouteWithNavigationExample
import com.example.makhazany.features.imports.presentation.ImportsNavigation
import com.example.makhazany.presentation.dashboard.ui.DashboardScreen
import com.example.makhazany.presentation.dashboard.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.makhazany.presentation.ui.StockScreen
import com.example.makhazany.presentation.ui.StockViewModel

sealed class NavigationState {
    object Dashboard : NavigationState()
    object Exports : NavigationState()
    object Imports : NavigationState()
    object Stock : NavigationState()
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Simple manual dependency injection as requested
        val localDataSource = LocalDataSource()
        val repository = DashboardRepositoryImpl(localDataSource)
        val getDashboardItemsUseCase = GetDashboardItemsUseCase(repository)

        setContent {
            DashboardTheme {
                var navigationState by remember { mutableStateOf<NavigationState>(NavigationState.Dashboard) }

                when (navigationState) {
                    NavigationState.Dashboard -> {
                        val viewModel = remember {
                            DashboardViewModel(getDashboardItemsUseCase)
                        }
                        DashboardScreen(
                            viewModel = viewModel,
                            onNavigateToExports = {
                                navigationState = NavigationState.Exports
                            },
                            onNavigateToImports = {
                                navigationState = NavigationState.Imports
                            },
                            onNavigateToStock = {
                                navigationState = NavigationState.Stock
                            }
                        )
                    }
                    NavigationState.Exports -> {
                        ExportsRouteWithNavigationExample(
                            onBackToMain = {
                                navigationState = NavigationState.Dashboard
                            }
                        )
                    }
                    NavigationState.Imports -> {
                        ImportsNavigation(
                            onBackToMain = {
                                navigationState = NavigationState.Dashboard
                            }
                        )
                    }
                    NavigationState.Stock -> {
                        StockScreen(
                            viewModel = hiltViewModel(),
                            onItemClick = { _, _ ->
                                // Handle item click if needed
                            }
                        )
                    }
                }
            }
        }
    }
}
