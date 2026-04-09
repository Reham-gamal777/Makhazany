package com.asma.makhazen_project.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun IncomingScreen(onBackClick: () -> Unit) {
    val themeColor = Color(0xFFA16965)
    
    Scaffold(
        containerColor = Color(0xFFF8F9FB),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        "إضافة توريد جديد",
                        fontWeight = FontWeight.Bold,
                        color = themeColor,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.NotificationsNone, contentDescription = null, tint = themeColor)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = null, tint = themeColor)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            // Top Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                        Text("العملية الحالية", color = Color.Gray, fontSize = 14.sp)
                        Text("تسجيل دفعة مالية", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(themeColor, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Payments, contentDescription = null, tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Main Form Container
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F4F8)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.End) {
                    
                    Text("ابحث عن اسم العميل", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("اكتب اسم العميل أو رقم هاتفه...", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                        trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF6EEED),
                            unfocusedContainerColor = Color(0xFFF6EEED),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("المبلغ المورد", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { 
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("0.00")
                                Text("EGP", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                        },
                        trailingIcon = { Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(20.dp)) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF6EEED),
                            unfocusedContainerColor = Color(0xFFF6EEED),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("طريقة التوريد", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PaymentMethodItem("إنستا باي", Icons.Default.Speed, false, themeColor, Modifier.weight(1f))
                        PaymentMethodItem("فودافون", Icons.Default.AccountBalanceWallet, false, themeColor, Modifier.weight(1f))
                        PaymentMethodItem("نقدي", Icons.Default.Payments, true, themeColor, Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("ملاحظات إضافية", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth().height(100.dp),
                        placeholder = { Text("أضف أي تفاصيل أخرى عن العملية هنا...", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF6EEED),
                            unfocusedContainerColor = Color(0xFFF6EEED),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Upload Section with dashed border
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.PhotoCamera, contentDescription = null, tint = Color.Gray)
                    Text("إرفاق صورة الإيصال (اختياري)", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Submit Button
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircleOutline, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("تأكيد التوريد وحفظ البيانات", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun PaymentMethodItem(
    name: String, 
    icon: androidx.compose.ui.graphics.vector.ImageVector, 
    isSelected: Boolean, 
    selectedColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(60.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) Color.White else Color.Transparent,
        border = if (isSelected) null else BorderStroke(1.dp, Color.Transparent)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) selectedColor else Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = name,
                fontSize = 12.sp,
                color = if (isSelected) selectedColor else Color.Gray
            )
        }
    }
}
