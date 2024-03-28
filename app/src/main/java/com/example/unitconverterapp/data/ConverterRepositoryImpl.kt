package com.example.unitconverterapp.data

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class ConverterRepositoryImpl @Inject constructor(private val dao: ConverterDAO) : ConverterRepository {
//class ConverterRepositoryImpl(private val dao: ConverterDAO) : ConverterRepository {

    override suspend fun insertResult(result: ConversionResult) {
        dao.insertResult(result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        dao.deleteResult(result)
    }

    override suspend fun deleteAllResults() {
        dao.deleteAll()
    }

    override fun getSavedResults(): Flow<List<ConversionResult>> {
        return dao.getResults()
    }
}