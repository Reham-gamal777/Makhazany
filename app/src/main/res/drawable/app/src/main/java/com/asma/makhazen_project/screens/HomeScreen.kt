package com.asma.makhazen_project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.asma.makhazen_project.R

@Composable
fun HomeScreen(
    onOutgoingClick: () -> Unit,
    onIncomingClick: () -> Unit,
    onCustomersClick: () -> Unit,
    onWarehousesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val itemsList = listOf(
        ItemsData("الصادر", "56 شحنة", R.drawable.exeport, Color(0xFFE3F2FD)),
        ItemsData("الوارد", "64 طلب", R.drawable.imp, Color(0xFFF1F8E9)),
        ItemsData("العملاء", "27 فاتورة", R.drawable.clients, Color(0xFFFFF3E0)),
        ItemsData("المخازن", "8 مخزن", R.drawable.repo, Color(0xFFF3E5F5)),
        ItemsData("مرتجع", "45 مرتجع", R.drawable.come, Color(0xFFFBE9E7)),
        ItemsData("توريد", "68 عملية", R.drawable.goo, Color(0xFFEFEBE9))
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(itemsList) { item ->
            CardView(
                item = item,
                onClick = {
                    when (item.title) {
                        "الصادر" -> onOutgoingClick()
                        "الوارد" -> onIncomingClick()
                        "العملاء" -> onCustomersClick()
                        "المخازن" -> onWarehousesClick()
                    }
                }
            )
        }
    }
}
