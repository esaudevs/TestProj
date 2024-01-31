package com.esaudev.testproj.di

import com.esaudev.testproj.data.repository.DefaultImageRepository
import com.esaudev.testproj.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsImageRepository(
        defaultImageRepository: DefaultImageRepository
    ): ImagesRepository
}