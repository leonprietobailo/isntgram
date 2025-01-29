package com.leprieto.isntgram.module

import android.content.Context
import androidx.room.Room
import com.leprieto.isntgram.db.AppDatabase
import com.leprieto.isntgram.query.db.UserDetailsLocalDao
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

//    @Provides
//    @Singleton
//    fun provideUserRepository(userDetailsDao: UserDetailsDao): RemoteUserRepository {
//        return RemoteUserRepository(userDetailsDao)
//    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDetailsLocalDao {
        return database.userDetailsLocalDao()
    }

}