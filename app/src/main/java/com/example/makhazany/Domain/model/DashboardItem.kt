package com.example.makhazany.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class DashboardItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val badgeText: String,
    val badgeIcon: ImageVector?,
    val mainIcon: ImageVector,
    val backgroundColor: Color,
    val iconTintColor: Color,
    val trendIcon: ImageVector? = null,
    val trendColor: Color? = null
)
