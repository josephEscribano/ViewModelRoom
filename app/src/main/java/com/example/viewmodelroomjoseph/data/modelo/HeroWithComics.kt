package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.Embedded
import androidx.room.Relation


data class HeroWithComics(
    @Embedded val hero: HeroEntity,
    @Relation(
        parentColumn = "heroId",
        entityColumn = "heroComicId"
    )
    val comics: List<ComicEntity>?
)
