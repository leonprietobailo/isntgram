package com.leprieto.isntgram.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leprieto.isntgram.dao.UserDao
import com.leprieto.isntgram.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}