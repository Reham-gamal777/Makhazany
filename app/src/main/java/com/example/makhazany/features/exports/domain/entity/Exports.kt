package com.example.makhazany.features.exports.domain.entity

data class ExportInvoice(
    val exportNumber: String,
    val exportDate: String,
    val clientName: String,
    val amount: String,
    val currency: String
)

data class ExportsSummary(
    val totalAmount: String,
    val totalCount: String,
    val clientsCount: String
)

