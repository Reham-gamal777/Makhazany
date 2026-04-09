package com.example.makhazany.features.imports.data.mapper

import android.R.attr.id
import com.example.makhazany.features.imports.data.model.ImportInvoiceDto
import com.example.makhazany.features.imports.data.model.ImportsSummaryDto
import com.example.makhazany.features.imports.domain.entity.ImportInvoice
import com.example.makhazany.features.imports.domain.entity.ImportsSummary

fun ImportInvoiceDto.toDomain() = ImportInvoice(
    id = id,
    invoiceNumber = invoiceNumber,
    clientName = clientName,
    importDate = importDate,
    amount = amount,
    currency = currency
)

fun ImportsSummaryDto.toDomain() = ImportsSummary(
    totalSalesToday = totalSalesToday,
    invoicesCount = invoicesCount,
    activeCustomersCount = activeCustomersCount,
    currency = currency
)
