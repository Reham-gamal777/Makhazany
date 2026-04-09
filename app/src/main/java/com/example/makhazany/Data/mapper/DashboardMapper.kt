package com.example.makhazany.data.mapper

import com.example.makhazany.data.model.DashboardItemLocalModel
import com.example.makhazany.domain.model.DashboardItem

fun DashboardItemLocalModel.toDomainModel(): DashboardItem {
    return DashboardItem(
        id = id,
        title = title,
        subtitle = subtitle,
        badgeText = "$unit $count", // Formatting logic belongs to mapper or UseCase
        badgeIcon = badgeIcon,
        mainIcon = mainIcon,
        backgroundColor = backgroundColor,
        iconTintColor = iconTintColor,
        trendIcon = trendIcon,
        trendColor = trendColor
    )
}
