package com.example.makhazany.features.exports.domain.usecase

import com.example.makhazany.core.util.Resource
import com.example.makhazany.features.exports.domain.entity.ExportsSummary
import com.example.makhazany.features.exports.domain.repository.ExportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExportsSummaryUseCase @Inject constructor(
    private val repository: ExportsRepository
) {
    operator fun invoke(): Flow<Resource<ExportsSummary>> {
        return repository.getExportsSummary()
    }
}
