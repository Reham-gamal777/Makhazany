package com.example.makhazany.features.imports.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.features.imports.domain.entity.ImportInvoice
import com.example.makhazany.features.imports.presentation.components.ImportsSearchBar
import com.example.makhazany.features.imports.presentation.components.ImportsTable
import com.example.makhazany.features.imports.presentation.components.SummaryCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportsScreen(
    state: ImportsUiState,
    onSearchQueryChanged: (String) -> Unit,
    onAddImportClick: () -> Unit,
    onItemClick: (ImportInvoice) -> Unit,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "الوارد",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF673AB7)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Notifications */ }) {
                            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                        IconButton(onClick = { /* TODO: Menu */ }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    )
                )
            },
            floatingActionButton = {
                SmallFloatingActionButton(
                    onClick = { /* FAB Action */ },
                    containerColor = Color(0xFF673AB7),
                    contentColor = Color.White,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "Scanner")
                }
            },
            containerColor = Color(0xFFF8F9FA)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                ImportsSearchBar(
                    query = state.query,
                    onQueryChange = onSearchQueryChanged
                )

                Button(
                    onClick = onAddImportClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.AddCircleOutline, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "إضافة وارد", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF673AB7))
                    }
                } else if (state.errorMessage != null) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = state.errorMessage, color = Color.Red)
                        Button(onClick = onRefresh) { Text("إعادة المحاولة") }
                    }
                } else {
                    ImportsTable(
                        items = state.items,
                        onItemClick = onItemClick
                    )

                    SummaryCards(summary = state.summary)
                }
            }
        }
    }
}
