package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.viewmodelroomjoseph.data.modelo.HeroEntity

@Entity(tableName = "comics",
foreignKeys = [
    ForeignKey(entity = HeroEntity::class,
    parentColumns = ["heroId"],
    childColumns = ["heroComicId"])
])
data class ComicEntity(
    @ColumnInfo(name = "comicName")
    val comicName: String,
    var heroComicId: Int= 0,
    @PrimaryKey(autoGenerate = true)
    val comicId: Int = 0,
)
