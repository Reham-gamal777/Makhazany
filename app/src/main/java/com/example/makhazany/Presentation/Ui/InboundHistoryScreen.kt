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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.ui.theme.*
import com.example.makhazany.data.local.entity.InboundEntity
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboundHistoryScreen(
    viewModel: InboundViewModel,
    itemName: String,
    onDismiss: () -> Unit
) {
    val history by viewModel.inboundHistory.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDismiss, modifier = Modifier.background(LightGray, CircleShape)) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = TextGray)
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "سجل شراء: $itemName",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryPurple
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.History, contentDescription = null, tint = PrimaryPurple)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(history) { inbound ->
                    InboundHistoryItem(inbound)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            

            Button(
                onClick = { showAddDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurple),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("إضافة عملية شراء جديدة", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

    if (showAddDialog) {
        AddInboundDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { amount, price, invoice ->

                viewModel.addInbound(
                    InboundEntity(
                        itemId = 1,
                        amount = amount,
                        pricePerUnit = price,
                        invoiceNumber = invoice,
                        inboundDate = System.currentTimeMillis()
                    )
                )
                showAddDialog = false
            }
        )
    }
}

@Composable
fun AddInboundDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int, Double, String) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var invoice by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("إضافة عملية شراء") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = invoice, onValueChange = { invoice = it }, label = { Text("رقم الفاتورة") })
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("الكمية") })
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("سعر الوحدة") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(amount.toIntOrNull() ?: 0, price.toDoubleOrNull() ?: 0.0, invoice)
            }) { Text("إضافة") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("إلغاء") }
        }
    )
}

@Composable
fun InboundHistoryItem(inbound: InboundEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .matchParentSize()
                    .background(Color(0xFFEBEFF1))
                    .align(Alignment.CenterStart)
            )
            
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "رقم الفاتورة: #${inbound.invoiceNumber}",
                        color = TextGray,
                        fontSize = 12.sp
                    )
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            formatDate(inbound.inboundDate),
                            color = Color(0xFFECF0F5),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(modifier = Modifier.size(8.dp).background(Color(0xFFF2F3F5), CircleShape))
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("سعر الوحدة", color = TextGray, fontSize = 12.sp)
                        Text(
                            "السعر: ${inbound.pricePerUnit} ج.م",
                            color = Color(0xFFE6ECF1),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.End) {
                        Text("الكمية المستلمة", color = TextGray, fontSize = 12.sp)
                        Text(
                            "الكمية: ${inbound.amount}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = TextDark
                        )
                    }
                }
            }
        }
    }
}

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd أكتوبر yyyy", Locale("ar"))
    return sdf.format(Date(timestamp))
}
