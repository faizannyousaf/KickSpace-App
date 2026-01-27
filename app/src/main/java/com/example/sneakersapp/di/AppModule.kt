package com.example.sneakersapp.di

import android.content.Context
import androidx.room.Room
import com.example.sneakersapp.UserPreferences
import com.example.sneakersapp.database.AppDatabase
import com.example.sneakersapp.model.Dao.SneakerDao
import com.example.sneakersapp.model.repositories.ReviewsRepository
import com.example.sneakersapp.model.repositories.SneakerRepository
import com.example.sneakersapp.model.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "sneaker_db"
        ).fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideReviewsRepository(appDatabase : AppDatabase) : ReviewsRepository {
        return ReviewsRepository(appDatabase.reviewDao())
    }

    @Singleton
    @Provides
    fun provideSneakerRepository(appDatabase : AppDatabase, @ApplicationContext context : Context) : SneakerRepository {
        return SneakerRepository(appDatabase.sneakerDao(), context )
    }

    @Singleton
    @Provides
    fun provideUserRepository(appDatabase : AppDatabase) : UserRepository {
        return UserRepository(appDatabase.userDao())
    }

    @Provides
    fun provideSneakerDao(database: AppDatabase): SneakerDao {
        return database.sneakerDao()
    }


//    @Singleton
//    @Provides
//    fun provideUserPreferences(appContext: Context) : UserPreferences {
//        return UserPreferences(appContext)
//    }
}