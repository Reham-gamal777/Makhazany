package com.example.makhazany.features.imports.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImportInvoiceDto(
    val id: String,
    val invoiceNumber: Int,
    val clientName: String,
    val importDate: String,
    val amount: Double,
    val currency: String
)

@Serializable
data class ImportInvoicesResponse(
    val items: List<ImportInvoiceDto>
)
