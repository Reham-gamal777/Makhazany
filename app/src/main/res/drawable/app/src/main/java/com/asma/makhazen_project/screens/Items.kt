package com.asma.makhazen_project.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.asma.makhazen_project.R

data class ItemsData(
    val title: String,
    val subTitle: String,
    val mainIcon: Int,
    val color: Color
)

@Composable
fun Items() {

    val items = listOf(
        ItemsData("الصادر", "56 شحنة", R.drawable.exeport, Color(0xFFE3F2FD)),
        ItemsData("الوارد", "64 طلب", R.drawable.imp, Color(0xFFF1F8E9)),
        ItemsData("العملاء", "27 فاتورة", R.drawable.clients, Color(0xFFFFF3E0)),
        ItemsData("المخازن", "8 مخزن", R.drawable.repo, Color(0xFFF3E5F5)),
        ItemsData("مرتجع", "45 مرتجع", R.drawable.come, Color(0xFFFBE9E7)),
        ItemsData("توريد", "68 عملية", R.drawable.goo, Color(0xFFEFEBE9))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items.forEach {
            CardView(item = it)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
