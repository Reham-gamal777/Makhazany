package com.example.makhazany.features.imports.data.datasource

import com.example.makhazany.features.imports.data.model.ImportInvoiceDto
import com.example.makhazany.features.imports.data.model.ImportsSummaryDto

interface ImportsLocalDataSource {
    suspend fun getImports(): List<ImportInvoiceDto>
    suspend fun getImportsSummary(): ImportsSummaryDto
}
