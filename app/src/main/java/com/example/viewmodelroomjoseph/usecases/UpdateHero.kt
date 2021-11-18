package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHeroEntity
import com.example.viewmodelroomjoseph.domain.Hero
import javax.inject.Inject

class UpdateHero @Inject constructor(private val heroRepository: HeroRepository) {
    fun invoke(hero: Hero) = heroRepository.updateHero(hero.toHeroEntity())
}
