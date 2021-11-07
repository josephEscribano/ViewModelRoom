package com.example.viewmodelroomjoseph.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class HeroWithElements(
    @Embedded val hero: HeroEntity,
    @Relation(
        parentColumn = "heroId",
        entityColumn = "heroCreatorId"
    )
    val elements: List<ElementsEntity>?
)
