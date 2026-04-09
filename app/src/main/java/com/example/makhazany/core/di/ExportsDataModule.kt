package com.example.makhazany.core.di

import android.content.Context
import android.content.res.AssetManager
import com.example.makhazany.features.exports.data.datasource.ExportsLocalDataSource
import com.example.makhazany.features.exports.data.datasource.ExportsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExportsDataModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideAssetManager(
        @ApplicationContext context: Context
    ): AssetManager = context.assets

    @Provides
    @Singleton
    fun provideExportsLocalDataSource(
        assetManager: AssetManager,
        json: Json
    ): ExportsLocalDataSource = ExportsLocalDataSourceImpl(assetManager, json)
}
