package com.example.makhazany.features.imports.data.datasource

import android.content.res.AssetManager
import com.example.makhazany.features.imports.data.model.ImportInvoiceDto
import com.example.makhazany.features.imports.data.model.ImportInvoicesResponse
import com.example.makhazany.features.imports.data.model.ImportsSummaryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ImportsLocalDataSourceImpl @Inject constructor(
    private val assetManager: AssetManager,
    private val json: Json
) : ImportsLocalDataSource {

    override suspend fun getImports(): List<ImportInvoiceDto> = withContext(Dispatchers.IO) {
        val jsonString = assetManager.open("imports/imports.json").bufferedReader().use { it.readText() }
        json.decodeFromString<ImportInvoicesResponse>(jsonString).items
    }

    override suspend fun getImportsSummary(): ImportsSummaryDto = withContext(Dispatchers.IO) {
        val jsonString = assetManager.open("imports/summary.json").bufferedReader().use { it.readText() }
        json.decodeFromString<ImportsSummaryDto>(jsonString)
    }
}
