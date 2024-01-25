package com.yuriyyg.domain.di

import com.yuriyyg.data.FlightRepositoryInterface
import com.yuriyyg.domain.mapper.SearchResponseToUIStateMapper
import com.yuriyyg.domain.usecases.SearchListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseDomain {

    @Provides
    @Singleton
    fun provideUseCase(repositoryInterface: FlightRepositoryInterface,
                       mapper: SearchResponseToUIStateMapper
    ) = SearchListUseCase(repositoryInterface, mapper)
}