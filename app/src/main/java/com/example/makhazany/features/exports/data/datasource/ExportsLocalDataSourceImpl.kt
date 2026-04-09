package com.example.makhazany.features.exports.data.datasource

import android.content.res.AssetManager
import com.example.makhazany.features.exports.data.model.ExportsPageDto
import com.example.makhazany.features.exports.data.model.ExportsSummaryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ExportsLocalDataSourceImpl @Inject constructor(
    private val assetManager: AssetManager,
    private val json: Json
) : ExportsLocalDataSource {

    override suspend fun getExports(): ExportsPageDto = withContext(Dispatchers.IO) {
        val jsonString = assetManager.open("exports/exports.json").bufferedReader().use { it.readText() }
        json.decodeFromString(jsonString)
    }

    override suspend fun getSummary(): ExportsSummaryDto = withContext(Dispatchers.IO) {
        val jsonString = assetManager.open("exports/summary.json").bufferedReader().use { it.readText() }
        json.decodeFromString(jsonString)
    }
}
