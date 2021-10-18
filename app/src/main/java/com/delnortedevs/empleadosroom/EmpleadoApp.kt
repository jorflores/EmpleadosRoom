package com.delnortedevs.empleadosroom

import android.app.Application
import androidx.room.Room
import com.delnortedevs.empleadosroom.db.AppDatabase

class EmpleadoApp : Application() {

   val database: AppDatabase by lazy {AppDatabase.getDatabase(this)}

/*   val room = Room
      .databaseBuilder(this,AppDatabase::class.java,"app_database")
      .build()*/

}