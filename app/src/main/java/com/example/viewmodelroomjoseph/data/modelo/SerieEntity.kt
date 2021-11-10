package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.viewmodelroomjoseph.data.modelo.HeroEntity

@Entity(tableName = "series",
    foreignKeys = [
        ForeignKey(entity = HeroEntity::class,
            parentColumns = ["heroId"],
            childColumns = ["heroSerieId"])
    ])
data class SerieEntity(
    @ColumnInfo(name = "serieName")
    val serieName: String,
    var heroSerieId: Int= 0,
    @PrimaryKey(autoGenerate = true)
    val serieid: Int = 0,
)
