package com.example.makhazany.features.imports.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImportsSummaryDto(
    val totalSalesToday: Double,
    val invoicesCount: Int,
    val activeCustomersCount: Int,
    val currency: String
)
