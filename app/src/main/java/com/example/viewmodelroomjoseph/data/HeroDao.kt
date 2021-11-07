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
    suspend fun insert(hero: HeroEntity)

    @Update
    fun updateHero(heroe: HeroEntity)

    @Delete
    fun deleteHero(heroe: HeroEntity)







}