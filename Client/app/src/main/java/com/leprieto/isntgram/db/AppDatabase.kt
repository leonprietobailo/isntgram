package com.leprieto.isntgram.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leprieto.isntgram.dao.RemoteUserDao
import com.leprieto.isntgram.model.RemoteUser

@Database(entities = [RemoteUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun remoteUserDao(): RemoteUserDao
//    abstract fun localSessionDao(): LocalSessionDao
//    abstract fun remoteSessionDao(): RemoteSessionDao
}
