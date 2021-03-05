package com.gsoft.argentina.guardaclaves.repository

import androidx.lifecycle.LiveData
import com.gsoft.argentina.guardaclaves.data.model.Entidad

interface Repository {

     fun getEntidades(): LiveData<MutableList<Entidad>>
    suspend fun saveEntidad(entidad:Entidad)
    suspend fun deleteEntidad(entidad: Entidad)
    suspend fun update(entidad: Entidad)
    suspend fun deleteTodo()

    //
}