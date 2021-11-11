package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHeroEntity
import com.example.viewmodelroomjoseph.domain.Hero

data class UpdateHero(private val heroRepository: HeroRepository) {
    fun invoke(hero: Hero) = heroRepository.updateHero(hero.toHeroEntity())
}
