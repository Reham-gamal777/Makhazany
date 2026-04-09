package com.example.makhazany.features.exports.data.datasource

import com.example.makhazany.features.exports.data.model.ExportsPageDto
import com.example.makhazany.features.exports.data.model.ExportsSummaryDto

interface ExportsLocalDataSource {
    suspend fun getExports(): ExportsPageDto
    suspend fun getSummary(): ExportsSummaryDto
}
