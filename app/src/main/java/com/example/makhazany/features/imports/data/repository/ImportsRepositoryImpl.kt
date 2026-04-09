package com.example.makhazany.features.imports.data.repository

import com.example.makhazany.core.util.AppError
import com.example.makhazany.core.util.Result
import com.example.makhazany.features.imports.data.datasource.ImportsLocalDataSource
import com.example.makhazany.features.imports.data.mapper.toDomain
import com.example.makhazany.features.imports.domain.entity.ImportInvoice
import com.example.makhazany.features.imports.domain.entity.ImportsSummary
import com.example.makhazany.features.imports.domain.repository.ImportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImportsRepositoryImpl @Inject constructor(
    private val localDataSource: ImportsLocalDataSource
) : ImportsRepository {

    override fun getImports(query: String?): Flow<Result<List<ImportInvoice>>> = flow {
        try {
            val dtos = localDataSource.getImports()
            val domainItems = dtos.map { it.toDomain() }
            
            val filteredItems = if (!query.isNullOrBlank()) {
                domainItems.filter { 
                    it.invoiceNumber.toString().contains(query, ignoreCase = true) ||
                    it.clientName.contains(query, ignoreCase = true)
                }
            } else {
                domainItems
            }
            
            emit(Result.Success(filteredItems))
        } catch (e: Exception) {
            emit(Result.Failure(AppError.ParsingError(e.message ?: "Unknown error")))
        }
    }

    override fun getImportsSummary(): Flow<Result<ImportsSummary>> = flow {
        try {
            val dto = localDataSource.getImportsSummary()
            emit(Result.Success(dto.toDomain()))
        } catch (e: Exception) {
            emit(Result.Failure(AppError.ParsingError(e.message ?: "Unknown error")))
        }
    }
}
