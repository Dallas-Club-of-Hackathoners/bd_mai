package stu.mai.bd_mai

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import stu.mai.bd_mai.database.AppDatabase

//@HiltAndroidApp
class App : Application() {
    val database: AppDatabase by lazy { AppDatabase.createDataBase(this) }
}