package com.example.makhazany.core.di

import com.example.makhazany.features.exports.data.repository.ExportsRepositoryImpl
import com.example.makhazany.features.exports.domain.repository.ExportsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ExportsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExportsRepository(
        impl: ExportsRepositoryImpl
    ): ExportsRepository
}
