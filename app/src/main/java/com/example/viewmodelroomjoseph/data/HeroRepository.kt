package com.example.viewmodelroomjoseph.data

import com.example.viewmodelroomjoseph.data.modelo.HeroEntity
import com.example.viewmodelroomjoseph.data.modelo.HeroWithSeriesAndComics
import com.example.viewmodelroomjoseph.domain.Hero

data class HeroRepository(private val heroDao: HeroDao){
    suspend fun getHeroes() = heroDao.getHeroes()
    suspend fun getHeroById(id: Int): HeroWithSeriesAndComics = heroDao.getHeroesById(id)
    suspend fun getComics() = heroDao.getComics()
    suspend fun getSeries() = heroDao.getSeries()
    suspend fun insertHero(hero: HeroEntity) = heroDao.insertHero(hero)
    fun updateHero(hero: HeroEntity) = heroDao.updateHero(hero)
    fun deleteHero(hero: HeroEntity) = heroDao.deleteHero(hero)
    suspend fun insertHeroWithElements(hero: HeroWithSeriesAndComics) = heroDao.insertHeroWithElements(hero)
}
