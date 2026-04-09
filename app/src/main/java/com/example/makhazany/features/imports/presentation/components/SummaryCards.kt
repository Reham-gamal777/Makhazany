package com.example.makhazany.features.imports.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.features.imports.domain.entity.ImportsSummary

@Composable
fun SummaryCards(
    summary: ImportsSummary?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        summary?.let {
            SummaryCard(
                label = "إجمالي مبيعات اليوم",
                value = "${it.totalSalesToday} ${it.currency}",
                accentColor = Color(0xFF673AB7),
                isLarge = true
            )
            SummaryCard(
                label = "عدد الفواتير",
                value = "${it.invoicesCount} فواتير",
                accentColor = Color(0xFF2196F3)
            )
            SummaryCard(
                label = "عملاء نشطون",
                value = "${it.activeCustomersCount} عملاء",
                accentColor = Color(0xFF8D6E63)
            )
        }
    }
}

@Composable
private fun SummaryCard(
    label: String,
    value: String,
    accentColor: Color,
    isLarge: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = value,
                    style = if (isLarge) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (isLarge) Color(0xFF673AB7) else accentColor
                )
            }
            
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(accentColor)
            )
        }
    }
}
