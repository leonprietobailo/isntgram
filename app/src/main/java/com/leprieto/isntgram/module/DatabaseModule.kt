package com.leprieto.isntgram.module

import android.content.Context
import androidx.room.Room
import com.leprieto.isntgram.dao.LocalSessionDao
import com.leprieto.isntgram.dao.RemoteSessionDao
import com.leprieto.isntgram.dao.RemoteUserDao
import com.leprieto.isntgram.db.AppDatabase
import com.leprieto.isntgram.model.LocalSessionRepository
import com.leprieto.isntgram.model.RemoteSessionRepository
import com.leprieto.isntgram.model.RemoteUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(remoteUserDao: RemoteUserDao): RemoteUserRepository {
        return RemoteUserRepository(remoteUserDao)
    }

    @Provides
    fun provideUserDao(database: AppDatabase): RemoteUserDao {
        return database.remoteUserDao()
    }

    @Provides
    @Singleton
    fun provideLocalSessionRepository(localSessionDao: LocalSessionDao): LocalSessionRepository {
        return LocalSessionRepository(localSessionDao)
    }

    @Provides
    fun provideLocalSessionDao(database: AppDatabase): LocalSessionDao {
        return database.localSessionDao()
    }

    @Provides
    @Singleton
    fun provideRemoteSessionRepository(remoteSessionDao: RemoteSessionDao): RemoteSessionRepository {
        return RemoteSessionRepository(remoteSessionDao)
    }

    @Provides
    fun provideRemoteSessionDao(database: AppDatabase): RemoteSessionDao {
        return database.remoteSessionDao()
    }
}