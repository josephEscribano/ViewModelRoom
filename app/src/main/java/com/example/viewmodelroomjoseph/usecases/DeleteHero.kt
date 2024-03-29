package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHeroWithSeriesAndComics
import com.example.viewmodelroomjoseph.domain.Hero
import javax.inject.Inject

class DeleteHero @Inject constructor(private val heroRepository: HeroRepository) {
    suspend fun invoke(hero: Hero) = heroRepository.deleteHero(hero.toHeroWithSeriesAndComics())
}