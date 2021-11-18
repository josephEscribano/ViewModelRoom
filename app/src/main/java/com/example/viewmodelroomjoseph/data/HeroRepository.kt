package com.example.viewmodelroomjoseph.data

import com.example.viewmodelroomjoseph.data.modelo.HeroEntity
import com.example.viewmodelroomjoseph.data.modelo.HeroWithSeriesAndComics
import javax.inject.Inject

class HeroRepository @Inject constructor(private val heroDao: HeroDao) {
    suspend fun getHeroes() = heroDao.getHeroes()
    fun updateHero(hero: HeroEntity) = heroDao.updateHero(hero)
    suspend fun deleteHero(hero: HeroWithSeriesAndComics) = heroDao.deleteHeroComicsAndSeries(hero)
    suspend fun insertHeroWithElements(hero: HeroWithSeriesAndComics) =
        heroDao.insertHeroWithElements(hero)
}
