package com.delnortedevs.empleadosroom.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Empleado (
    @PrimaryKey (autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name ="nombre") val nombre: String,
    @NonNull @ColumnInfo(name ="departamento") val departamento: String,
    @NonNull @ColumnInfo(name ="email") val email: String
        ) {

    constructor (nombre: String, departamento: String, email: String) : this(0,nombre,departamento,email) {

    }


}