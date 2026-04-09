package com.example.makhazany.features.exports.domain.entity

data class ExportsPage(
    val items: List<ExportInvoice>,
    val totalCount: Int
)
