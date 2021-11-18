package com.example.viewmodelroomjoseph.data

import androidx.room.*
import com.example.viewmodelroomjoseph.data.modelo.ComicEntity
import com.example.viewmodelroomjoseph.data.modelo.HeroEntity
import com.example.viewmodelroomjoseph.data.modelo.HeroWithSeriesAndComics
import com.example.viewmodelroomjoseph.data.modelo.SerieEntity

@Dao
interface HeroDao {
    @Transaction
    @Query("select * from heroes")
    suspend fun getHeroes(): List<HeroWithSeriesAndComics>

    @Query("delete from comics where heroComicId = :id")
    suspend fun deleteComics(id: Int)

    @Query("delete from series where heroSerieId = :id")
    suspend fun deleteSeries(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHero(hero: HeroEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListComics(elements: List<ComicEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListSeries(elements: List<SerieEntity>)

    @Transaction
    suspend fun insertHeroWithElements(heroWithSeriesAndComics: HeroWithSeriesAndComics) {
        heroWithSeriesAndComics.heroWithComics.hero.heroId =
            insertHero(heroWithSeriesAndComics.heroWithComics.hero).toInt()
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


    @Transaction
    suspend fun deleteHeroComicsAndSeries(heroWithSeriesAndComics: HeroWithSeriesAndComics) {
        deleteComics(heroWithSeriesAndComics.heroWithComics.hero.heroId)
        deleteSeries(heroWithSeriesAndComics.heroWithComics.hero.heroId)
        deleteHero(heroWithSeriesAndComics.heroWithComics.hero)
    }


}