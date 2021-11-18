package com.example.viewmodelroomjoseph.data.di

import android.content.Context
import androidx.room.Room
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, HeroRoomDatabase::class.java, context.getString(R.string.db))
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun heroDao(heroRoomDatabase: HeroRoomDatabase) = heroRoomDatabase.heroDao()
}