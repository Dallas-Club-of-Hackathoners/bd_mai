package stu.mai.bd_mai.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stu.mai.bd_mai.database.AppDatabase


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        app: Application
    ) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "PRODUCT_ORDERS"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
