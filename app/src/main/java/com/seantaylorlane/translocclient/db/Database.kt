package com.seantaylorlane.translocclient.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.seantaylorlane.translocclient.api.TranslocModels

@Database(entities = arrayOf(TranslocModels.AgenciesResponse.Agency::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun agenciesDao(): AgenciesDao
}