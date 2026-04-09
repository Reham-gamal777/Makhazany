package com.example.makhazany.features.exports.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExportsPageDto(
    val items: List<ExportInvoiceDto>
)
