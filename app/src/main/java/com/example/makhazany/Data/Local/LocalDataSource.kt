package com.example.makhazany.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import com.example.makhazany.core.theme.*
import com.example.makhazany.data.model.DashboardItemLocalModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSource {
    fun fetchDashboardItems(): Flow<List<DashboardItemLocalModel>> = flow {
        // Simulating network delay for realistic experience
        delay(800)
        emit(
            listOf(
                DashboardItemLocalModel(
                    id = 1,
                    title = "الصادر",
                    subtitle = "تمت معالجتها اليوم بنجاح",
                    count = 56,
                    unit = "شحنة",
                    badgeIcon = Icons.Default.Inventory2,
                    mainIcon = Icons.Default.LocalShipping,
                    backgroundColor = OutboundBlue,
                    iconTintColor = OutboundIconTint,
                    trendIcon = Icons.Default.NorthEast,
                    trendColor = OutboundIconTint
                ),
                DashboardItemLocalModel(
                    id = 2,
                    title = "الوارد",
                    subtitle = "في انتظار الفحص والاستلام",
                    count = 64,
                    unit = "طلب",
                    badgeIcon = Icons.Default.Description,
                    mainIcon = Icons.Default.Inventory,
                    backgroundColor = InboundOrange,
                    iconTintColor = InboundIconTint,
                    trendIcon = Icons.Default.SouthEast,
                    trendColor = InboundIconTint
                ),
                DashboardItemLocalModel(
                    id = 3,
                    title = "العملاء",
                    subtitle = "نشطون حالياً في عمليات التوصيل",
                    count = 27,
                    unit = "فاتورة",
                    badgeIcon = Icons.Default.PeopleOutline,
                    mainIcon = Icons.Default.Groups,
                    backgroundColor = CustomersGreen,
                    iconTintColor = CustomersIconTint
                ),
                DashboardItemLocalModel(
                    id = 4,
                    title = "المخازن",
                    subtitle = "السعة التشغيلية بلغت 85%",
                    count = 8,
                    unit = "مخزن",
                    badgeIcon = Icons.Default.HomeWork,
                    mainIcon = Icons.Default.Storefront,
                    backgroundColor = WarehousesBlueGrey,
                    iconTintColor = WarehousesIconTint
                ),
                DashboardItemLocalModel(
                    id = 5,
                    title = "المرتجع",
                    subtitle = "مراجعة العناصر المرتجعة جارية",
                    count = 45,
                    unit = "مرتجع",
                    badgeIcon = Icons.Default.Restore,
                    mainIcon = Icons.Default.SettingsBackupRestore,
                    backgroundColor = ReturnsPink,
                    iconTintColor = ReturnsIconTint
                ),
                DashboardItemLocalModel(
                    id = 6,
                    title = "توريد",
                    subtitle = "أوامر توريد مالية معلقة",
                    count = 68,
                    unit = "عملية",
                    badgeIcon = Icons.Default.Visibility,
                    mainIcon = Icons.Default.MonetizationOn,
                    backgroundColor = SupplyPurple,
                    iconTintColor = SupplyIconTint
                )
            )
        )
    }
}
