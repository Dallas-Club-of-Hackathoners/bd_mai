package stu.mai.bd_mai.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stu.mai.bd_mai.features.orders.repositories.CheckRepository
import stu.mai.bd_mai.features.orders.repositories.CheckRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

//    @Binds
//    abstract fun bindCreatingOrderRepository(
//
//    )
//
//    @Binds
//    abstract fun bindCardOrderRepository(
//
//    )

    @Binds
    abstract fun bindCheckRepository(
        checkRepository: CheckRepositoryImpl
    ): CheckRepository

//    @Binds
//    abstract fun bindNewParamsRepository(
//
//    )



}