package com.asma.makhazen_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun OutgoingScreen(onBackClick: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFFE3F2FD), // اللون المطلوب للخلفية
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE3F2FD) // اللون المطلوب للتوب بار
                ),
                title = {
                    Text(
                        "الصادرات",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF2196F3))
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color(0xFF2196F3))
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = "Notifications", tint = Color(0xFF2196F3))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFE3F2FD)) // اللون المطلوب للعمود الرئيسي
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            // Search Bar
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("بحث برقم الفاتورة أو العميل", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add Button
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AddCircleOutline, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("إضافة صادر", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Table Headers
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("المبلغ", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("اسم العميل", modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("تاريخ التصدير", modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("رقم الفاتورة", modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            // List of Invoices
            val invoices = listOf(
                InvoiceData("#1250", "2026-03-27", "المورد ش نوره عبد العال", "0 ج"),
                InvoiceData("#1251", "2026-03-28", "شركة النيل للتجارة", "100 ج"),
                InvoiceData("#1252", "2026-03-28", "مؤسسة الأمل الصناعية", "300 ج"),
                InvoiceData("#1253", "2026-03-29", "حسن محمد - توريدات", "0 ج"),
                InvoiceData("#1254", "2026-03-29", "محل البركة للأدوات", "150 ج")
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(invoices) { invoice ->
                    InvoiceRow(invoice)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Summary Cards
            SummaryCard("إجمالي مبيعات اليوم", "19,450 ج.م", Color(0xFF1979C5))
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard("عدد الفواتير", "5 فواتير", Color(0xFF4D70EA))
            Spacer(modifier = Modifier.height(8.dp))
            SummaryCard("عملاء نشطون", "4 عملاء", Color(0xFF869FDA))
        }
    }
}

data class InvoiceData(val id: String, val date: String, val customer: String, val amount: String)

@Composable
fun InvoiceRow(invoice: InvoiceData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(invoice.amount, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 14.sp)
            Text(invoice.customer, modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center, fontSize = 14.sp)
            Text(invoice.date, modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center, fontSize = 14.sp)
            Text(invoice.id, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 14.sp, color = Color(0xFF2196F3), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SummaryCard(title: String, value: String, accentColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.width(4.dp).height(40.dp).background(accentColor))
            Column(horizontalAlignment = Alignment.End) {
                Text(title, fontSize = 12.sp, color = Color.Gray)
                Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = accentColor)
            }
        }
    }
}
