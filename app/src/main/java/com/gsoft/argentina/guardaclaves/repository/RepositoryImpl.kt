package com.gsoft.argentina.guardaclaves.repository

import androidx.lifecycle.LiveData
import com.gsoft.argentina.guardaclaves.data.local.DataSource
import com.gsoft.argentina.guardaclaves.data.model.Entidad


class RepositoryImpl (private val dataSource : DataSource): Repository {

    override  fun getEntidades(): LiveData<MutableList<Entidad>> {
        return dataSource.getEtidades()
    }

    override suspend fun saveEntidad(entidad: Entidad) {
        return dataSource.saveEntidad(entidad)
    }

    override suspend fun deleteEntidad(entidad: Entidad) {
       return  dataSource.deleteEntidad(entidad)
    }

    override suspend fun update(entidad: Entidad) {
       return dataSource.update(entidad)
    }
    override suspend fun deleteTodo() {
        return dataSource.deleteAll()
    }

}