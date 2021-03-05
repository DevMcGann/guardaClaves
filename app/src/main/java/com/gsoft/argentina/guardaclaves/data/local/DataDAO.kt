package com.gsoft.argentina.guardaclaves.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gsoft.argentina.guardaclaves.data.model.Entidad

@Dao
interface DataDAO {

    @Query("SELECT * FROM entidad")
     fun getEntidades(): LiveData<MutableList<Entidad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEntidad (entidad : Entidad)

    @Delete
    suspend fun deleteEntidad(entidad: Entidad)

    @Update
    suspend fun  update(entidad : Entidad)

    @Query("DELETE  FROM entidad")
    suspend fun deleteAll()


}