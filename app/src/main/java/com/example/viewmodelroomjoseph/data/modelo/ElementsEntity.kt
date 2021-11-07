package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "elements",
    foreignKeys = [
        ForeignKey(entity = HeroEntity::class,
            parentColumns = ["heroId"],
            childColumns = ["heroCreatorId"])
    ])
data class ElementsEntity(
    @PrimaryKey(autoGenerate = true)
    val elementId:Int,
    var heroCreatorId: Int = 0,
    @Embedded val comic: ComicEntity,
    @Embedded val serie: SerieEntity,
)
