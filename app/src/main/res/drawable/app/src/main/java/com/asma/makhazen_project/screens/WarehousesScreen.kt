package com.asma.makhazen_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarehousesScreen(onBackClick: () -> Unit) {
    val themeColor = Color(0xFF673AB7)

    Scaffold(
        containerColor = Color(0xFFF8F9FB),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "المخازن",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = themeColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = themeColor)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = "Notifications", tint = themeColor)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = themeColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = themeColor,
                contentColor = Color.White,
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.QrCodeScanner, contentDescription = "Scan")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            // Action Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.FileUpload, contentDescription = null, tint = themeColor)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("استيراد +", color = themeColor, fontWeight = FontWeight.Bold)
                        }
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = themeColor)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AddBox, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("منتج جديد +", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Overview Section
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F4F8))
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.End) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("نظرة عامة على الرصيد", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.Inventory, contentDescription = null, tint = themeColor)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OverviewCard("نواقص", "7", Color(0xFF8D6E63), Modifier.weight(1f))
                            OverviewCard("حركة اليوم", "24", Color(0xFF1976D2), Modifier.weight(1f))
                            OverviewCard("إجمالي الأصناف", "142", themeColor, Modifier.weight(1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Inventory List Section
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                modifier = Modifier.weight(1f).height(48.dp),
                                placeholder = { Text("بحث سريع...", fontSize = 12.sp) },
                                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp)) },
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedContainerColor = Color(0xFFF5F5F5),
                                    focusedContainerColor = Color(0xFFF5F5F5),
                                    unfocusedBorderColor = Color.Transparent,
                                    focusedBorderColor = Color.Transparent
                                )
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("قائمة الجرد", fontWeight = FontWeight.Bold, color = themeColor, fontSize = 18.sp)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Table Header
                        Row(
                            modifier = Modifier.fillMaxWidth().background(Color(0xFFF8F9FA)).padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("الرصيد", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray)
                            Text("التصنيف", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray)
                            Text("الصنف", modifier = Modifier.weight(2f), textAlign = TextAlign.End, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray)
                        }

                        // Table Content
                        val itemsList = listOf(
                            InventoryItemData("لمبه ديسكو", "SKU: DISCO-001", "إضاءة", "100", Color(0xFFF3E5F5)),
                            InventoryItemData("كابل توصيل 5 متر", "SKU: CBL-5M", "كهرباء", "450", Color(0xFFE3F2FD)),
                            InventoryItemData("بطارية شحن جاف", "SKU: BAT-DRY-12", "طاقة", "12", Color(0xFFFFF3E0))
                        )

                        itemsList.forEach { inventoryItem ->
                            InventoryRow(inventoryItem)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Pagination
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("السابق >", color = Color.Gray, fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            PaginationButton("1", true)
                            PaginationButton("2", false)
                            PaginationButton("3", false)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("< التالي", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OverviewCard(title: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, color = Color.Gray, fontSize = 10.sp)
            Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = color)
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(color))
        }
    }
}

data class InventoryItemData(val name: String, val sku: String, val category: String, val balance: String, val iconBg: Color)

@Composable
fun InventoryRow(item: InventoryItemData) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(item.balance, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = themeColor)
            Text(item.category, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, color = Color.Gray, fontSize = 12.sp)
            Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(item.name, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text(item.sku, color = Color.Gray, fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier.size(36.dp).background(item.iconBg, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Lightbulb, contentDescription = null, tint = themeColor, modifier = Modifier.size(20.dp))
                }
            }
        }
        Divider(color = Color(0xFFF5F5F5), thickness = 1.dp)
    }
}

@Composable
fun PaginationButton(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(32.dp)
            .background(if (isSelected) Color(0xFF673AB7) else Color.Transparent, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = if (isSelected) Color.White else Color.Gray, fontSize = 12.sp)
    }
}

private val themeColor = Color(0xFF673AB7)
