package com.example.makhazany.features.imports.domain.repository

import com.example.makhazany.core.util.Result
import com.example.makhazany.features.imports.domain.entity.ImportInvoice
import com.example.makhazany.features.imports.domain.entity.ImportsSummary
import kotlinx.coroutines.flow.Flow

interface ImportsRepository {
    fun getImports(query: String? = null): Flow<Result<List<ImportInvoice>>>
    fun getImportsSummary(): Flow<Result<ImportsSummary>>
}
