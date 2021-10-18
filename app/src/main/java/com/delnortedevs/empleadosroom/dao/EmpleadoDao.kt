package com.delnortedevs.empleadosroom.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.delnortedevs.empleadosroom.model.Empleado

@Dao
interface EmpleadoDao  {

    @Query("SELECT * FROM Empleado order by nombre ASC")
    suspend fun getAll() : List<Empleado>

/*    @Query("SELECT * from Empleado where nombre = :nombre")
    fun getByName(nombre: String)
    */
    @Insert
    suspend fun insertEmpleado(empleado: Empleado) : Long

    @Update
    suspend fun updateEmpleado(empleado: Empleado)

    @Delete
    suspend fun deleteempleado(empleado: Empleado)

}