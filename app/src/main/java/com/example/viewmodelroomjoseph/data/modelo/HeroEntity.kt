package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "heroes")
data class HeroEntity(
    @PrimaryKey(autoGenerate = true)
    val heroId: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: LocalDate,
    @ColumnInfo(name = "name")
    val heroName: String,
)