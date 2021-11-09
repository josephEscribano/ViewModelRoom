package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHeroWithElements
import com.example.viewmodelroomjoseph.domain.Hero

data class InsertHeroWithElements(val heroRepository: HeroRepository){
    suspend fun invoke(hero:Hero) = heroRepository.insertHeroWithElements(hero.toHeroWithElements())
}
