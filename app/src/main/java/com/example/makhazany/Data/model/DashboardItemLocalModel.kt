package com.example.makhazany.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Representing the structure of our local data.
 * Even though we don't use DB, we keep this to simulate a real data source model.
 */
data class DashboardItemLocalModel(
    val id: Int,
    val title: String,
    val subtitle: String,
    val count: Int,
    val unit: String,
    val badgeIcon: ImageVector?,
    val mainIcon: ImageVector,
    val backgroundColor: Color,
    val iconTintColor: Color,
    val trendIcon: ImageVector? = null,
    val trendColor: Color? = null
)
