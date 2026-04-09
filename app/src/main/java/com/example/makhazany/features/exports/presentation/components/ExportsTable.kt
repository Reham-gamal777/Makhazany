package com.example.makhazany.features.exports.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.features.exports.domain.entity.ExportInvoice

@Composable
fun ExportsTable(
    items: List<ExportInvoice>,
    modifier: Modifier = Modifier,
    onExportClick: (ExportInvoice) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0))
                    .padding(8.dp)
            ) {
                TableHeaderItem("رقم الفاتورة", Modifier.weight(1f))
                TableHeaderItem("تاريخ التصدير", Modifier.weight(1.2f))
                TableHeaderItem("اسم العميل", Modifier.weight(1.5f))
                TableHeaderItem("المبلغ", Modifier.weight(1f))
            }
            
            items.forEach { item ->
                HorizontalDivider(color = Color(0xFFEEEEEE))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onExportClick(item) }
                        .padding(8.dp)
                ) {
                    Text(
                        text = "#${item.exportNumber}",
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = item.exportDate,
                        modifier = Modifier.weight(1.2f),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = item.clientName,
                        modifier = Modifier.weight(1.5f),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "${item.currency} ${item.amount}",
                        modifier = Modifier.weight(1f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Composable
private fun TableHeaderItem(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        textAlign = TextAlign.Center
    )
}
