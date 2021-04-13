package com.gsoft.argentina.guardaclaves.data.local

import androidx.lifecycle.LiveData
import com.gsoft.argentina.guardaclaves.data.model.Entidad


class DataSource (private val dataDao : DataDAO) {
     fun getEtidades(): LiveData<MutableList<Entidad>> {
        return dataDao.getEntidades()
    }

     suspend fun getEntidad(id:Int) : Entidad{
        return dataDao.getEntidad(id)
    }

    suspend fun saveEntidad(entidad: Entidad){
        return dataDao.saveEntidad(entidad)
    }

    suspend fun deleteEntidad(entidad: Entidad){
        return dataDao.deleteEntidad(entidad)
    }

    suspend fun update(entidad: Entidad){
        return dataDao.update(entidad)
    }

    suspend fun deleteAll(){
        dataDao.deleteAll()
    }
}