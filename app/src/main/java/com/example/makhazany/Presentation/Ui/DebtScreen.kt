package com.example.makhazany.presentation.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.data.local.entity.CustomerEntity
import com.example.makhazany.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebtScreen(viewModel: CustomerViewModel) {
    val customers by viewModel.customers.collectAsState()
    val totalDebt by viewModel.totalDebt.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("إدارة المخزن", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                navigationIcon = { IconButton(onClick = {}) { Icon(Icons.Default.Menu, contentDescription = null) } },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Notifications, contentDescription = null) }
                    IconButton(onClick = {}) { Icon(Icons.Default.Search, contentDescription = null) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = PrimaryPurple,
                contentColor = Color.White,
                shape = CircleShape
            ) {

                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(LightGray)
        ) {
            // Header Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                colors = CardDefaults.cardColors(containerColor = PrimaryPurple),
                shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("إجمالي مديونيات العملاء", color = Color.White.copy(alpha = 0.8f), fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "${String.format("%.2f", totalDebt)} ج.م",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .offset(y = (-20).dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FilterList, contentDescription = null, tint = PrimaryPurple)
                        Text(" تصفية النتائج", fontSize = 14.sp)
                    }
                    VerticalDivider(modifier = Modifier.height(20.dp).padding(horizontal = 8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ترتيب حسب الأحدث ", fontSize = 14.sp)
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }
            }

            Row(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("قائمة المديونيات", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Surface(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "${customers.size} عميل",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = TextGray
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(customers) { customer ->
                    CustomerDebtItem(customer)
                }
            }
        }
    }
}

@Composable
fun CustomerDebtItem(customer: CustomerEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                color = SecondaryPurple,
                shape = CircleShape
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        customer.customerName.take(1),
                        color = PrimaryPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(customer.customerName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(customer.customerType + " - " + customer.address, color = TextGray, fontSize = 12.sp)
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "${customer.customerDebt} ج.م",
                    color = DebtRed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text("آخر تحديث: منذ ساعتين", color = TextGray, fontSize = 10.sp)
            }
        }
    }
}
