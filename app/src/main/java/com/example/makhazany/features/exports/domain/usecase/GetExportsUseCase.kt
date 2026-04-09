package com.example.makhazany.features.exports.domain.usecase

import com.example.makhazany.core.util.Resource
import com.example.makhazany.features.exports.domain.entity.ExportsPage
import com.example.makhazany.features.exports.domain.repository.ExportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExportsUseCase @Inject constructor(
    private val repository: ExportsRepository
) {
    operator fun invoke(query: String, page: Int, limit: Int = 10): Flow<Resource<ExportsPage>> {
        return repository.getExports(query, page, limit)
    }
}
