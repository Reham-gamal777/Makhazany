package com.example.makhazany.core.di

import com.example.makhazany.data.repository.OutboundRepositoryImpl
import com.example.makhazany.domain.repository.OutboundRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOutboundRepository(
        outboundRepositoryImpl: OutboundRepositoryImpl
    ): OutboundRepository
}
