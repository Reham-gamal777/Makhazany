package com.example.makhazany.features.imports.domain.usecase

import com.example.makhazany.core.util.Result
import com.example.makhazany.features.imports.domain.entity.ImportsSummary
import com.example.makhazany.features.imports.domain.repository.ImportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImportsSummaryUseCase @Inject constructor(
    private val repository: ImportsRepository
) {
    operator fun invoke(): Flow<Result<ImportsSummary>> {
        return repository.getImportsSummary()
    }
}
