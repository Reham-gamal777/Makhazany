package com.example.makhazany.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.data.local.relation.ItemWithStock
import com.example.makhazany.data.local.entity.ItemEntity
import com.example.makhazany.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockScreen(
    viewModel: StockViewModel,
    onItemClick: (Int, String) -> Unit,
    onBack: () -> Unit
) {
    val stockItems by viewModel.stockItems.collectAsState()
    val totalItems by viewModel.totalItems.collectAsState()
    val shortages by viewModel.shortages.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("المخازن", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null) } },
                actions = { IconButton(onClick = {}) { Icon(Icons.Default.Menu, contentDescription = null) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(LightGray)
        ) {

            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f).height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.AddBox, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("منتج جديد")
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f).height(56.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryPurple),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.UploadFile, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("استيراد")
                }
            }


            Card(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Inventory2, contentDescription = null, tint = PrimaryPurple)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("نظرة عامة على الرصيد", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatItem("إجمالي الأصناف", totalItems.toString(), PrimaryPurple)
                        StatItem("حركة اليوم", "24", Color.Blue)
                        StatItem("نواقص", shortages.toString(), Color.Red)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            Card(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("قائمة الجرد", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = PrimaryPurple)
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("بحث سريع...", fontSize = 12.sp) },
                            modifier = Modifier.width(180.dp).height(48.dp),
                            shape = RoundedCornerShape(8.dp),
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(modifier = Modifier.fillMaxWidth().background(LightGray.copy(alpha = 0.5f)).padding(8.dp)) {
                        Text("الصنف", modifier = Modifier.weight(1.5f), fontSize = 12.sp, textAlign = TextAlign.End)
                        Text("التصنيف", modifier = Modifier.weight(1f), fontSize = 12.sp, textAlign = TextAlign.Center)
                        Text("الرصيد", modifier = Modifier.weight(1f), fontSize = 12.sp, textAlign = TextAlign.Start)
                    }

                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(stockItems) { item ->
                            StockListItem(item, onItemClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Card(
        modifier = Modifier.width(100.dp),
        colors = CardDefaults.cardColors(containerColor = LightGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, fontSize = 10.sp, color = TextGray)
            Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = color)
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier.height(2.dp).fillMaxWidth().background(color))
        }
    }
}

@Composable
fun StockListItem(
    itemWithStock: ItemWithStock,
    onItemClick: (Int, String) -> Unit
) {
    val item = itemWithStock.item
    val stock = itemWithStock.stock

    Column(modifier = Modifier.clickable { onItemClick(item.id, item.name) }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1.5f), verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(32.dp),
                    color = SecondaryPurple,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Lightbulb, contentDescription = null, tint = PrimaryPurple, modifier = Modifier.padding(4.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(item.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("SKU: ${item.sku ?: "N/A"}", fontSize = 10.sp, color = TextGray)
                }
            }
            Text(item.category ?: "عام", modifier = Modifier.weight(1f), fontSize = 12.sp, textAlign = TextAlign.Center, color = TextGray)
            Text("${stock?.quantity?.toInt() ?: 0}", modifier = Modifier.weight(1f), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryPurple, textAlign = TextAlign.Start)
        }
        HorizontalDivider(color = LightGray)
    }
}
