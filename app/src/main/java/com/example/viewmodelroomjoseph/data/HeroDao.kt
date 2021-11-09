package com.example.viewmodelroomjoseph.data

import androidx.room.*
import com.example.viewmodelroomjoseph.data.modelo.*

@Dao
interface HeroDao {
    @Transaction
    @Query("select * from heroes")
    suspend fun getHeroes(): List<HeroWithElements>
    @Transaction
    @Query("select * from heroes where heroId = :id")
    suspend fun getHeroesById(id: Int) : HeroWithElements

    @Query("select * from comics")
    suspend fun getComics(): List<ComicEntity>

    @Query("select * from series")
    suspend fun getSeries(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHero(hero: HeroEntity) :Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListElements(elements: List<ElementsEntity>)

    @Transaction
    suspend fun insertHeroWithElements(heroWithElements: HeroWithElements){
        heroWithElements.hero.heroId = insertHero(heroWithElements.hero).toInt()
        heroWithElements.elements?.apply {
            forEach { it.heroCreatorId = heroWithElements.hero.heroId }
            insertListElements(this)
        }

    }
    @Update
    fun updateHero(heroe: HeroEntity)

    @Delete
    fun deleteHero(heroe: HeroEntity)







}