package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class HeroWithSeriesAndComics(
    @Embedded val heroWithComics: HeroWithComics,
    @Relation(
        entity = SerieEntity::class,
        parentColumn = "heroId",
        entityColumn = "heroSerieId"

    )
    val series: List<SerieEntity>?
)
