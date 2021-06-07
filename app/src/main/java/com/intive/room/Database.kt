package com.intive.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [User::class, Event::class, EventToUser::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao?
}
