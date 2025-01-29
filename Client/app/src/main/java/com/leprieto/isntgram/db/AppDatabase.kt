package com.leprieto.isntgram.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leprieto.isntgram.model.db.UserDetailsLocal
import com.leprieto.isntgram.query.db.UserDetailsLocalDao

@Database(entities = [UserDetailsLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDetailsLocalDao(): UserDetailsLocalDao
//    abstract fun localSessionDao(): LocalSessionDao
//    abstract fun remoteSessionDao(): RemoteSessionDao
}
