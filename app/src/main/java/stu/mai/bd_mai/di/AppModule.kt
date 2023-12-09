package stu.mai.bd_mai.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import stu.mai.bd_mai.database.AppDatabase
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
//    @Provides
//    fun provideAppDatabase(
//        @ApplicationContext app: Context
//    ) = Room.databaseBuilder(
//        app,
//        AppDatabase::class.java,
//        "PRODUCT_ORDERS"
//    ).build() // The reason we can construct a database for the repo
//
//    @Singleton
//    @Provides
//    fun provideCommonDao(db: AppDatabase) =
//        db.getCommonDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideCustomerDao(db: AppDatabase) =
//        db.getCustomerDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideExecutorDao(db: AppDatabase) =
//        db.getExecutorDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideMaterialDao(db: AppDatabase) =
//        db.getMaterialDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideOrderDao(db: AppDatabase) =
//        db.getOrderDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideProductDao(db: AppDatabase) =
//        db.getProductDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideMaterialsInProductDao(db: AppDatabase) =
//        db.getMaterialsInProductDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideProductInOrderDao(db: AppDatabase) =
//        db.getProductInOrderDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideSupplierDao(db: AppDatabase) =
//        db.getSupplierDao() // The reason we can implement a Dao for the database
//
//    @Singleton
//    @Provides
//    fun provideMaterialsSuppliersDao(db: AppDatabase) =
//        db.getMaterialsSuppliersDao() // The reason we can implement a Dao for the database
//
//}