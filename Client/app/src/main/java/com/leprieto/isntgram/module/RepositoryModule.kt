package com.leprieto.isntgram.module

import com.leprieto.isntgram.query.db.UserDetailsLocalDao
import com.leprieto.isntgram.repository.db.UserDetailsLocalRepository
import com.leprieto.isntgram.source.db.UserDetailsLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // ********************
    // *     SOURCES      *
    // ********************
    @Provides
    fun provideLocalUserDetailsSource(userDetailsDao: UserDetailsLocalDao): UserDetailsLocalSource {
        return UserDetailsLocalSource(userDetailsDao)
    }

    // ********************
    // *   REPOSITORIES   *
    // ********************
    @Provides
    fun provideUserDetailsLocalRepository(userDetailsLocalSource: UserDetailsLocalSource): UserDetailsLocalRepository {
        return UserDetailsLocalRepository(userDetailsLocalSource)
    }

}