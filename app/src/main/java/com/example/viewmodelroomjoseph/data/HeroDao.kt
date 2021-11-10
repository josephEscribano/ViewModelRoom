package com.example.viewmodelroomjoseph.data

import androidx.room.*
import com.example.viewmodelroomjoseph.data.modelo.*

@Dao
interface HeroDao {
    @Transaction
    @Query("select * from heroes")
    suspend fun getHeroes(): List<HeroWithSeriesAndComics>
    @Transaction
    @Query("select * from heroes where heroId = :id")
    suspend fun getHeroesById(id: Int) : HeroWithSeriesAndComics

    @Query("select * from comics")
    suspend fun getComics(): List<ComicEntity>

    @Query("select * from series")
    suspend fun getSeries(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHero(hero: HeroEntity) :Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListComics(elements: List<ComicEntity>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListSeries(elements: List<SerieEntity>)

    @Transaction
    suspend fun insertHeroWithElements(heroWithSeriesAndComics: HeroWithSeriesAndComics){
        heroWithSeriesAndComics.heroWithComics.hero.heroId = insertHero(heroWithSeriesAndComics.heroWithComics.hero).toInt()
        heroWithSeriesAndComics.heroWithComics.comics?.apply {
            forEach { it.heroComicId = heroWithSeriesAndComics.heroWithComics.hero.heroId }
            insertListComics(this)
        }
        heroWithSeriesAndComics.series?.apply {
            forEach { it.heroSerieId = heroWithSeriesAndComics.heroWithComics.hero.heroId }
            insertListSeries(this)
        }

    }
    @Update
    fun updateHero(heroe: HeroEntity)

    @Delete
    fun deleteHero(heroe: HeroEntity)







}