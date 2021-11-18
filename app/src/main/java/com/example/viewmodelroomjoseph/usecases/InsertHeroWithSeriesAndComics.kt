package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHeroWithSeriesAndComics
import com.example.viewmodelroomjoseph.domain.Hero
import javax.inject.Inject

class InsertHeroWithSeriesAndComics @Inject constructor(private val heroRepository: HeroRepository) {
    suspend fun invoke(hero: Hero) =
        heroRepository.insertHeroWithElements(hero.toHeroWithSeriesAndComics())
}
