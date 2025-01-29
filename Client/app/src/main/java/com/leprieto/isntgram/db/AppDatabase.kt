package com.leprieto.isntgram.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leprieto.isntgram.model.common.UserDetails
import com.leprieto.isntgram.query.db.UserDetailsDao

@Database(entities = [UserDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun remoteUserDao(): UserDetailsDao
//    abstract fun localSessionDao(): LocalSessionDao
//    abstract fun remoteSessionDao(): RemoteSessionDao
}
