//package com.example.unitconverterapp.di
//
//import com.example.unitconverterapp.data.ConverterDAO
//import com.example.unitconverterapp.data.ConverterRepositoryImpl
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//    @Singleton
//    @Provides
//    fun provideRepository(dao: ConverterDAO) =
//        ConverterRepositoryImpl(dao)
//
//}