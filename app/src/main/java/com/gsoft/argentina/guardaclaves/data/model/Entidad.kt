package com.gsoft.argentina.guardaclaves.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entidad(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,


    @ColumnInfo(name = "titulo")
    val titulo: String = "Titulo",


    @ColumnInfo(name = "usuario")
    val usuario: String = "Usuario",

    @ColumnInfo(name = "password")
    val password: String = "Password"
)