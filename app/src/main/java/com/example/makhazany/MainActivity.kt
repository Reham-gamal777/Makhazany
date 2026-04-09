package com.example.makhazany

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.makhazany.core.theme.DashboardTheme
import com.example.makhazany.data.local.LocalDataSource
import com.example.makhazany.data.repository.DashboardRepositoryImpl
import com.example.makhazany.domain.usecase.GetDashboardItemsUseCase
import com.example.makhazany.features.exports.presentation.ExportsRouteWithNavigationExample
import com.example.makhazany.features.imports.presentation.ImportsNavigation
import com.example.makhazany.presentation.dashboard.ui.DashboardScreen
import com.example.makhazany.presentation.dashboard.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

sealed class NavigationState {
    object Dashboard : NavigationState()
    object Exports : NavigationState()
    object Imports : NavigationState()
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
                }
            }
        }
    }
}
