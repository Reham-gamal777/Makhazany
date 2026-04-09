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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen(onBackClick: () -> Unit) {
    val Theme = Color(0xFFA16965)
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "مديونات العملاء",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Theme
                    )
                },
                navigationIcon = {
                    Row {
                        IconButton(onClick = { /* Search */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                        }
                        IconButton(onClick = { /* Notifications */ }) {
                            Icon(Icons.Default.NotificationsNone, contentDescription = "Notifications", tint = Color.Gray)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Gray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Theme,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.FileDownload, contentDescription = "Download")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
        ) {
            // Purple Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "إجمالي مديونيات العملاء",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "ج.م",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
                    )
                    Text(
                        text = "2,522,053.53",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Filter/Sort Bar
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FilterList, contentDescription = null, tint = Theme)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("تصفية النتائج", color = Theme)
                    }
                    Box(modifier = Modifier.width(1.dp).height(20.dp).background(Color.LightGray))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ترتيب حسب الأحدث", color = Color.Gray)
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
                    }
                }
            }

            // List Title and Badge
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color.LightGray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        "143 عميل",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    "قائمة المديونيات",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            // Customer List
            val customers = listOf(
                CustomerData("محمد سعودي الكنيسة", "عميل جملة - الجيزة", "6850.0 ج.م", "منذ ساعتين", Color(0xFFE1BEE7), "م"),
                CustomerData("أحمد الشناوي", "عميل قطاعي - القاهرة", "12,400.0 ج.م", "بالأمس", Color(0xFFC5CAE9), "أ"),
                CustomerData("شركة الفهد للتوريدات", "مورد تجاري - الإسكندرية", "420.75 ج.م", "منذ 3 أيام", Color(0xFFFFE0B2), "ش"),
                CustomerData("محمود عبد اللطيف", "عميل VIP - أسيوط", "95,000.0 ج.م", "منذ شهر", Color(0xFFE1BEE7), "م")
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(customers) { customer ->
                    CustomerItem(customer)
                }
                item {
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F4F8)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("تحميل المزيد من العملاء", color = Theme)
                    }
                }
            }
        }
    }
}

data class CustomerData(
    val name: String,
    val info: String,
    val amount: String,
    val lastUpdate: String,
    val avatarColor: Color,
    val initial: String
)

@Composable
fun CustomerItem(customer: CustomerData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                Text(customer.amount, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("آخر تحديث: ${customer.lastUpdate}", color = Color.Gray, fontSize = 10.sp)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(horizontalAlignment = Alignment.End) {
                Text(customer.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(customer.info, color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(customer.avatarColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(customer.initial, color = customer.avatarColor.copy(alpha = 1f), fontWeight = FontWeight.Bold)
            }
        }
    }
}
