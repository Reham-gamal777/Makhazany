package com.example.makhazany.features.imports.domain.entity

data class ImportInvoice(
    val id: String,
    val invoiceNumber: Int,
    val clientName: String,
    val importDate: String,
    val amount: Double,
    val currency: String
)
