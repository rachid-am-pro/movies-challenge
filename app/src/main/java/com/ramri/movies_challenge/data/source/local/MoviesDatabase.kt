package com.ramri.movies_challenge.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramri.movies_challenge.data.source.local.dao.MovieDao
import com.ramri.movies_challenge.data.source.local.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
internal abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
