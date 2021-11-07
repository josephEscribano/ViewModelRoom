package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHeroEntity
import com.example.viewmodelroomjoseph.domain.Hero

data class InsertHero(val heroRepository: HeroRepository) {
    suspend fun invoke(hero: Hero) = heroRepository.insertHero(hero.toHeroEntity())
}