package com.example.unitconverterapp.di

import android.content.Context
import androidx.room.Room
import com.example.unitconverterapp.data.ConverterDAO
import com.example.unitconverterapp.data.ConverterDatabase
import com.example.unitconverterapp.data.ConverterRepository
import com.example.unitconverterapp.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ConverterDatabase::class.java,
        "converter_data_database"
    ).build()

    @Singleton
    @Provides

    fun provideDao(database: ConverterDatabase) = database.converterDAO()


    @Singleton
    @Provides
    fun provideRepository(dao: ConverterDAO): ConverterRepository =
        ConverterRepositoryImpl(dao)




}