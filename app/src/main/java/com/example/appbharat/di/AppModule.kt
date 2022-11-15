package com.example.appbharat.di

import com.example.appbharat.MainRepoImpl
import com.example.appbharat.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Binds
    abstract fun bindMainRepoImpl(mainRepoImpl: MainRepoImpl): MainRepository
}