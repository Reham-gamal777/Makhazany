package com.example.makhazany.features.imports.di

import com.example.makhazany.features.imports.data.datasource.ImportsLocalDataSource
import com.example.makhazany.features.imports.data.datasource.ImportsLocalDataSourceImpl
import com.example.makhazany.features.imports.data.repository.ImportsRepositoryImpl
import com.example.makhazany.features.imports.domain.repository.ImportsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImportsModule {

    @Binds
    @Singleton
    abstract fun bindImportsLocalDataSource(
        impl: ImportsLocalDataSourceImpl
    ): ImportsLocalDataSource

    @Binds
    @Singleton
    abstract fun bindImportsRepository(
        impl: ImportsRepositoryImpl
    ): ImportsRepository
}
