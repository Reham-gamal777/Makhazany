package com.example.makhazany.features.imports.domain.entity

data class ImportsSummary(
    val totalSalesToday: Double,
    val invoicesCount: Int,
    val activeCustomersCount: Int,
    val currency: String
)
