package com.example.makhazany.core.di

import com.example.makhazany.data.repository.OutboundRepositoryImpl
import com.example.makhazany.data.repository.InboundRepositoryImpl
import com.example.makhazany.data.repository.StockRepositoryImpl
import com.example.makhazany.data.repository.CustomerRepositoryImpl
import com.example.makhazany.domain.repository.OutboundRepository
import com.example.makhazany.domain.repository.InboundRepository
import com.example.makhazany.domain.repository.StockRepository
import com.example.makhazany.domain.repository.CustomerRepository
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

    @Binds
    @Singleton
    abstract fun bindInboundRepository(
        inboundRepositoryImpl: InboundRepositoryImpl
    ): InboundRepository

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        customerRepositoryImpl: CustomerRepositoryImpl
    ): CustomerRepository
}
