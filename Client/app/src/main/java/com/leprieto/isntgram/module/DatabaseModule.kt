package com.leprieto.isntgram.module

import android.content.Context
import androidx.room.Room
import com.leprieto.isntgram.db.AppDatabase
import com.leprieto.isntgram.query.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

//    @Provides
//    fun provideUserDao(database: AppDatabase): UserDetailsDao {
//        return database.remoteUserDao()
//    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build() // Optionally add custom adapters if needed
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.1.150:8080/api/").client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideLocalSessionRepository(localSessionDao: LocalSessionDao): LocalSessionRepository {
//        return LocalSessionRepository(localSessionDao)
//    }
//
//    @Provides
//    fun provideLocalSessionDao(database: AppDatabase): LocalSessionDao {
//        return database.localSessionDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRemoteSessionRepository(remoteSessionDao: RemoteSessionDao): RemoteSessionRepository {
//        return RemoteSessionRepository(remoteSessionDao)
//    }
//
//    @Provides
//    fun provideRemoteSessionDao(database: AppDatabase): RemoteSessionDao {
//        return database.remoteSessionDao()
//    }
}