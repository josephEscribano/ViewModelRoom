package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.viewmodelroomjoseph.data.modelo.HeroEntity

@Entity(tableName = "series",
    foreignKeys = [
        ForeignKey(entity = ElementsEntity::class,
            parentColumns = ["elementId"],
            childColumns = ["elementSerieId"])
    ])
data class SerieEntity(
    @ColumnInfo(name = "serieName")
    val serieName: String,
    var elementSerieId: Int= 0,
    @PrimaryKey(autoGenerate = true)
    val serieid: Int = 0,
)
