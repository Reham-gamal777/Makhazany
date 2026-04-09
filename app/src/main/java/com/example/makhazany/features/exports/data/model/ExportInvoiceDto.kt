package com.example.makhazany.features.exports.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExportInvoiceDto(
    val id: String,
    val exportNumber: Int,
    val clientName: String,
    val exportDate: String,
    val amount: Double,
    val currency: String
)
