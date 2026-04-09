package com.example.makhazany.features.exports.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExportsSummaryDto(
    val totalSalesToday: Double,
    val invoicesCount: Int,
    val activeCustomersCount: Int,
    val currency: String
)
