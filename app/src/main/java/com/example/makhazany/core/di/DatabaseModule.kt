package com.example.makhazany.core.di

import android.content.Context
import androidx.room.Room
import com.example.makhazany.data.local.dao.*
import com.example.makhazany.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "makhazany_db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(db: AppDatabase): CustomerDao = db.customerDao()

    @Provides
    @Singleton
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()

    @Provides
    @Singleton
    fun provideStockDao(db: AppDatabase): StockDao = db.stockDao()

    @Provides
    @Singleton
    fun provideInboundDao(db: AppDatabase): InboundDao = db.inboundDao()

    @Provides
    @Singleton
    fun provideOutboundDao(db: AppDatabase): OutboundDao = db.outboundDao()

    @Provides
    @Singleton
    fun provideOutboundDetailsDao(db: AppDatabase): OutboundDetailsDao = db.outboundDetailsDao()

    @Provides
    @Singleton
    fun provideReturnedDao(db: AppDatabase): ReturnedDao = db.returnedDao()

    @Provides
    @Singleton
    fun provideReturnedDetailsDao(db: AppDatabase): ReturnedDetailsDao = db.returnedDetailsDao()

    @Provides
    @Singleton
    fun providePaymentDao(db: AppDatabase): PaymentDao = db.paymentDao()
}
