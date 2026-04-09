package com.example.makhazany.features.exports.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.makhazany.features.exports.domain.entity.ExportInvoice
import com.example.makhazany.features.exports.presentation.components.ExportsTable
import com.example.makhazany.features.exports.presentation.components.SearchBar
import com.example.makhazany.features.exports.presentation.components.SummaryCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportsScreen(
    onBackClick: () -> Unit,
    onAddExportClick: () -> Unit,
    onExportClick: (ExportInvoice) -> Unit,
    viewModel: ExportsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("الصادرات", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowForward, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Menu */ }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        IconButton(onClick = { /* Notifications */ }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* FAB Action */ },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(Icons.Default.GridView, contentDescription = "FAB")
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    SearchBar(
                        query = uiState.query,
                        onQueryChange = viewModel::onSearchQueryChanged
                    )
                }

                item {
                    Button(
                        onClick = onAddExportClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("إضافة صادر")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (uiState.isLoading && uiState.items.isEmpty()) {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                } else if (uiState.items.isEmpty() && !uiState.isLoading) {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                            Text("لا توجد فواتير تصدير حالياً", color = Color.Gray)
                        }
                    }
                } else {
                    item {
                        ExportsTable(items = uiState.items, onExportClick = onExportClick)
                    }
                    
                    if (uiState.canLoadMore) {
                        item {
                            TextButton(onClick = viewModel::loadMore, modifier = Modifier.padding(16.dp)) {
                                Text("تحميل المزيد")
                            }
                        }
                    }
                }

                item {
                    uiState.summary?.let {
                        SummaryCards(summary = it)
                    }
                }
                
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
}
