package com.jusang.randomcall.di

import com.jusang.randomcall.repository.ContactRepository
import com.jusang.randomcall.repository.LocalContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindContactRepository(localContactRepository: LocalContactRepository): ContactRepository
}