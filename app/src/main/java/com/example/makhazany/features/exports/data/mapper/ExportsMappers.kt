package com.example.makhazany.features.exports.data.mapper

import com.example.makhazany.features.exports.data.model.ExportInvoiceDto
import com.example.makhazany.features.exports.data.model.ExportsSummaryDto
import com.example.makhazany.features.exports.domain.entity.ExportInvoice
import com.example.makhazany.features.exports.domain.entity.ExportsSummary

fun ExportInvoiceDto.toEntity(): ExportInvoice {
    return ExportInvoice(
        exportNumber = exportNumber.toString(),
        exportDate = exportDate,
        clientName = clientName,
        amount = amount.toString(),
        currency = currency
    )
}

fun ExportsSummaryDto.toEntity(): ExportsSummary {
    return ExportsSummary(
        totalAmount = totalSalesToday.toString(),
        totalCount = invoicesCount.toString(),
        clientsCount = activeCustomersCount.toString()
    )
}
