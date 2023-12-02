package stu.mai.bd_mai

import android.app.Application
import androidx.room.Room
import stu.mai.bd_mai.database.AppDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "PRODUCT_ORDERS"
        ).build()
    }
}