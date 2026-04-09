package com.example.makhazany.features.exports.domain.repository

import com.example.makhazany.core.util.Resource
import com.example.makhazany.features.exports.domain.entity.ExportsPage
import com.example.makhazany.features.exports.domain.entity.ExportsSummary
import kotlinx.coroutines.flow.Flow

interface ExportsRepository {
    fun getExports(query: String, page: Int, limit: Int): Flow<Resource<ExportsPage>>
    fun getExportsSummary(): Flow<Resource<ExportsSummary>>
}
