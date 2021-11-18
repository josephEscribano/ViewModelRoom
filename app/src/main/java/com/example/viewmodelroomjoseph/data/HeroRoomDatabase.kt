package com.example.viewmodelroomjoseph.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.viewmodelroomjoseph.data.modelo.ComicEntity
import com.example.viewmodelroomjoseph.data.modelo.HeroEntity
import com.example.viewmodelroomjoseph.data.modelo.SerieEntity

@Database(
    entities = [HeroEntity::class, ComicEntity::class, SerieEntity::class],
    version = 15,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class HeroRoomDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao

}