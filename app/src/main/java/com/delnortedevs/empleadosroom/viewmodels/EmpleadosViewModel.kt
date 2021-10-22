package com.delnortedevs.empleadosroom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.delnortedevs.empleadosroom.dao.EmpleadoDao
import com.delnortedevs.empleadosroom.model.Empleado

class EmpleadosViewModel (private val empleadoDao: EmpleadoDao) : ViewModel()
{
    fun getAll() : LiveData<List<Empleado>> = empleadoDao.getAll()

    suspend fun insertEmpleado(empleado: Empleado) = empleadoDao.insertEmpleado(empleado)

    suspend fun getByName(nombre: String) : Empleado = empleadoDao.getByName(nombre)

    suspend fun updateEmpleado(empleado: Empleado) = empleadoDao.updateEmpleado(empleado)

    suspend fun deleteEmpleado(empleado: Empleado) = empleadoDao.deleteEmpleado(empleado)


}

class EmpleadosViewModelFactory (private val empleadoDao: EmpleadoDao) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmpleadosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmpleadosViewModel(empleadoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}