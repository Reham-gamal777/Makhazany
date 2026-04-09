package com.example.makhazany.features.imports.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.makhazany.features.imports.domain.entity.ImportInvoice

@Composable
fun ImportsTable(
    items: List<ImportInvoice>,
    onItemClick: (ImportInvoice) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableHeaderItem("المبلغ", 1f)
                TableHeaderItem("اسم العميل", 1.5f)
                TableHeaderItem("تاريخ الوارد", 1.2f)
                TableHeaderItem("رقم الفاتورة", 1f)
            }

            if (items.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "لا توجد بيانات", color = Color.Gray)
                }
            } else {
                items.forEach { item ->
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(item) }
                            .padding(vertical = 16.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TableRowContent(item)
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.TableHeaderItem(text: String, weight: Float) {
    Text(
        text = text,
        modifier = Modifier.weight(weight),
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RowScope.TableRowContent(item: ImportInvoice) {
    Text(
        text = "${item.amount}\n${item.currency}",
        modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    Text(
        text = item.clientName,
        modifier = Modifier.weight(1.5f),
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        maxLines = 2
    )

    Text(
        text = item.importDate,
        modifier = Modifier.weight(1.2f),
        style = MaterialTheme.typography.bodySmall,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )

    Text(
        text = "#${item.invoiceNumber}",
        modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF673AB7),
        textAlign = TextAlign.Center
    )
}
