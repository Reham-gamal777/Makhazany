package com.example.makhazany.presentation.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makhazany.core.theme.AppDimens
import com.example.makhazany.core.theme.White
import com.example.makhazany.domain.model.DashboardItem

@Composable
fun DashboardCard(
    item: DashboardItem,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        shape = RoundedCornerShape(AppDimens.cardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = item.backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppDimens.paddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(AppDimens.iconBackgroundSize)
                    .background(White.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.mainIcon,
                    contentDescription = null,
                    tint = item.iconTintColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(AppDimens.paddingMedium))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF37474F)
                    )
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF78909C),
                        fontSize = 12.sp
                    ),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(AppDimens.paddingSmall))

                Surface(
                    shape = RoundedCornerShape(AppDimens.badgeCornerRadius),
                    color = White.copy(alpha = 0.8f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        item.badgeIcon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = item.badgeText,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            item.trendIcon?.let {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.TopEnd) {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = item.trendColor ?: item.iconTintColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
