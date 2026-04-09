package com.example.makhazany.features.exports.data.repository

import com.example.makhazany.core.util.Resource
import com.example.makhazany.features.exports.data.datasource.ExportsLocalDataSource
import com.example.makhazany.features.exports.data.mapper.toEntity
import com.example.makhazany.features.exports.domain.entity.ExportsPage
import com.example.makhazany.features.exports.domain.entity.ExportsSummary
import com.example.makhazany.features.exports.domain.repository.ExportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExportsRepositoryImpl @Inject constructor(
    private val localDataSource: ExportsLocalDataSource
) : ExportsRepository {

    override fun getExports(query: String, page: Int, limit: Int): Flow<Resource<ExportsPage>> = flow {
        emit(Resource.Loading())
        try {
            val dto = localDataSource.getExports()
            val allItems = dto.items.map { it.toEntity() }
            
            val filteredItems = if (query.isBlank()) {
                allItems
            } else {
                allItems.filter { 
                    it.exportNumber.toString().contains(query) || 
                    it.clientName.contains(query, ignoreCase = true) 
                }
            }
            
            val totalCount = filteredItems.size
            val fromIndex = (page - 1) * limit
            val toIndex = minOf(fromIndex + limit, totalCount)
            
            val pagedItems = if (fromIndex < totalCount) {
                filteredItems.subList(fromIndex, toIndex)
            } else {
                emptyList()
            }
            
            emit(Resource.Success(ExportsPage(items = pagedItems, totalCount = totalCount)))
        } catch (e: Exception) {
            emit(Resource.Error("خطأ في قراءة البيانات: ${e.localizedMessage}"))
        }
    }

    override fun getExportsSummary(): Flow<Resource<ExportsSummary>> = flow {
        emit(Resource.Loading())
        try {
            val dto = localDataSource.getSummary()
            emit(Resource.Success(dto.toEntity()))
        } catch (e: Exception) {
            emit(Resource.Error("خطأ في قراءة الملخص: ${e.localizedMessage}"))
        }
    }
}
