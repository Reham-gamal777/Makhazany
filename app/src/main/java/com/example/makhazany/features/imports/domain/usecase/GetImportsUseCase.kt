package com.example.makhazany.features.imports.domain.usecase

import com.example.makhazany.core.util.Result
import com.example.makhazany.features.imports.domain.entity.ImportInvoice
import com.example.makhazany.features.imports.domain.repository.ImportsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImportsUseCase @Inject constructor(
    private val repository: ImportsRepository
) {
    operator fun invoke(query: String? = null): Flow<Result<List<ImportInvoice>>> {
        return repository.getImports(query)
    }
}
